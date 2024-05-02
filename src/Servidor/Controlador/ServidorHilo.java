/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servidor.Controlador;

import Servidor.Modelo.ConnSocket;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.speech.AudioException;
import javax.speech.EngineException;

/**
 *
 * @author Juan
 */
public class ServidorHilo extends Thread{

    private Control control;
    private String nombreUsuario;
    private final ConnSocket conexionCliente;
    private GestorLectura voz;

    public ServidorHilo(Control control, ConnSocket conexionCliente, String nombreUsuario) {
        this.conexionCliente = conexionCliente;
        this.control = control;
        this.nombreUsuario = nombreUsuario;
        control.setHiloHablando(this);
        try {
            voz = new GestorLectura(control);
            voz.leer("Bienvenido " + nombreUsuario);
        } catch (Exception ex) {
            control.getVista().mostrarMensaje("Revisar caracteristica de lectura");
            
        }
    }

    @Override
    public void run() {

        manejoAcciones();

        try {
            conexionCliente.cerrar();
            
        } catch (Exception et) {
            control.getVista().mostrarMensaje("No se puede cerrar el socket");
        }
    }

    private void manejoAcciones() {
        while (true) {
            try {
                int opcion = conexionCliente.getEntrada().readInt();

                switch (opcion) {

                    case 1 -> {
                        control.setHiloHablando(this);
                        String msj = conexionCliente.getEntrada().readUTF();
                        voz.leer(msj);
                        control.getVista().mostrarMensaje(msj + "\nSe reprodujo una frase");
                    }
                    case 2 -> {
                        control.setHiloHablando(this);
                        voz.leer("Hasta luego " + nombreUsuario);
                        control.getVista().mostrarMensaje("Bye " + nombreUsuario);
                    }

                }

            } catch (IOException e) {
                
                break;
            } catch(AudioException | EngineException | InterruptedException ex){
                control.getVista().mostrarMensaje("Error al leer mensajes de cliente " + nombreUsuario);
                break;
            }
        }
    }

    public ConnSocket getConexionCliente() {
        return conexionCliente;
    }

}
