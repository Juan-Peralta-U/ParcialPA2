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
import Servidor.Modelo.UsuarioVO;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.speech.AudioException;
import javax.speech.EngineErrorEvent;
import javax.speech.EngineEvent;
import javax.speech.EngineException;
import javax.speech.synthesis.SynthesizerEvent;

/**
 *
 * @author Juan
 */
public class Control{

    private final VistaConsola vista;
    private final ArchivoPropiedades properties;
    private final ConnServerSocket servidor;
    private final UsuarioDAO gestorUsuarios;
    private ServidorHilo hiloHablando;

    public Control() {
        vista = new VistaConsola();
        servidor = new ConnServerSocket();
        properties = new ArchivoPropiedades(new FileChooser("Seleccione datos de conexion").getFile());
        gestorUsuarios = new UsuarioDAO(this);
        

        loopServidor();

    }

    public void loopServidor() {

        try {
            int puerto1 = Integer.parseInt(properties.getData("Puerto.1"));
            int puerto2 = Integer.parseInt(properties.getData("Puerto.2"));
            servidor.runServer(puerto1, puerto2);
            while (true) {

                vista.mostrarMensaje("Servidor >> Esperando cliente");

                ConnSocket tempcon = new ConnSocket();
                tempcon.aceptarClientes(servidor.getServ(), servidor.getServ2());
                ServidorHilo userThread;

                String usuario = tempcon.getEntrada().readUTF();
                String contraseña = tempcon.getEntrada().readUTF();
                
                UsuarioVO user = new UsuarioVO();
                user.setUsuario(usuario);
                user.setContraseña(contraseña);
                
                if (gestorUsuarios.consultaUsuario(user)) {
                    vista.mostrarMensajeEmergente("Servidor >> Conexion recibida");
                    tempcon.getSalida().writeInt(1);
                    userThread = new ServidorHilo(this, tempcon, usuario);
                    
                    userThread.start();
                    userThread.getConexionCliente().getSalida().writeInt(1);
                    userThread.getConexionCliente().getSalida().writeUTF("El cliente esta registrado");
                    
                } else {
                    
                    tempcon.getSalida().writeInt(0);
                    tempcon.getSalida().writeUTF("El cliente no esta registrado");
                    vista.mostrarMensajeEmergente("Servidor >> El cliente NO existe");
                }

            }
        } catch (NumberFormatException ex) {
            vista.mostrarMensaje("No se pudo arrancar servidor. Revisar puertos ingresados.");
        } catch (IOException ex) {
            vista.mostrarMensaje("Accept failed: " + ex.getMessage());
            vista.mostrarMensaje("Fallo al conectar");
        }

    }

    public VistaConsola getVista() {
        return vista;
    }

    public ArchivoPropiedades getProperties() {
        return properties;
    }

    public ServidorHilo getHiloHablando() {
        return hiloHablando;
    }

    public void setHiloHablando(ServidorHilo hiloHablando) {
        this.hiloHablando = hiloHablando;
    }
}
