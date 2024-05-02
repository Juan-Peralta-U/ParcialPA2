/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servidor.Controlador;

import Servidor.Vista.VistaConsola;
import Servidor.Vista.FileChooser;
import Servidor.Modelo.ArchivoPropiedades;
import Servidor.Modelo.ConnServerSocket;
import Servidor.Modelo.ConnSocket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.speech.AudioException;
import javax.speech.EngineException;

/**
 *
 * @author Juan
 */
public class Control {

    VistaConsola vista;
    GestorLectura leer;
    ArchivoPropiedades puertos;
    ConnServerSocket servidor;
    UsuarioDAO gestorUsuarios;

    public Control() {

        try {
            leer = new GestorLectura();
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        } catch (EngineException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        }
        vista = new VistaConsola();
        servidor = new ConnServerSocket();
        puertos = new ArchivoPropiedades(new FileChooser("Seleccione puertos").getFile());
        gestorUsuarios = new UsuarioDAO();
        loopServidor();

    }

    public void loopServidor() {

        try {

            int puerto1 = Integer.parseInt(puertos.getData("Puerto.1"));
            int puerto2 = Integer.parseInt(puertos.getData("Puerto.2"));
            servidor.runServer(puerto1, puerto2);
            vista.mostrarMensaje(".::Servidor activo :");
            while (true) {

                vista.mostrarMensaje(".::Esperando Conexion :");

                ConnSocket tempcon = new ConnSocket();
                tempcon.aceptarClientes(servidor.getServ(), servidor.getServ2());
                ServidorHilo user;

                String usuario = tempcon.getEntrada().readUTF();
                String contraseña = tempcon.getEntrada().readUTF();

                if (gestorUsuarios.consultaUsuario(usuario, contraseña)) {
                    tempcon.getSalida().writeInt(1);
                    user = new ServidorHilo(this, tempcon, usuario);
                    user.start();
                } else {

                    tempcon.getSalida().writeInt(0);
                    tempcon.getSalida().writeUTF("El clinete no esta registrado");
                }

            }
        } catch (NumberFormatException ex) {
            vista.mostrarMensaje("No se pudo arrancar servidor. Revisar puertos ingresados.");
        } catch (IOException ex) {
            vista.mostrarMensaje("Accept failed: " + vista + ", " + ex.getMessage());
            vista.mostrarMensaje("Fallo al conectar");
        }

    }

    public VistaConsola getVista() {
        return vista;
    }

}
