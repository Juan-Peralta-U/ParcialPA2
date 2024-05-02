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
 * Clase ServidorHilo que extiende de Thread y maneja la comunicación con un cliente.
 * 
 * @author Juan
 */
public class ServidorHilo extends Thread{

     /**
     * Objeto Control que representa el controlador del servidor.
     */
    private Control control;

    /**
     * Nombre de usuario del cliente.
     */
    private String nombreUsuario;

    /**
     * Objeto ConnSocket que representa la conexión con el cliente.
     */
    private final ConnSocket conexionCliente;

    /**
     * Objeto GestorLectura que se encarga de la lectura en voz alta.
     */
    private GestorLectura voz;

    /**
     * Constructor de la clase ServidorHilo.
     *
     * @param control          Objeto Control que representa el controlador del servidor.
     * @param conexionCliente  Objeto ConnSocket que representa la conexión con el cliente.
     * @param nombreUsuario    Nombre de usuario del cliente.
     */
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

    /**
     * Método que se ejecuta en el hilo.
     * Maneja las acciones del cliente.
     */
    @Override
    public void run() {

        manejoAcciones();

        try {
            conexionCliente.cerrar();
            
        } catch (Exception et) {
            control.getVista().mostrarMensaje("No se puede cerrar el socket");
        }
    }

    /**
     * Método que maneja las acciones del cliente.
     */
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

    /**
     * Método que obtiene el objeto ConnSocket con las salidas y entradas de información que se necesitarán.
     *
     * @return El objeto ConnSocket.
     */
    public ConnSocket getConexionCliente() {
        return conexionCliente;
    }

}
