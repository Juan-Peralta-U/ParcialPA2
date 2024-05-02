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
 * Clase encargada de comunicar las distintas partes del programa
 * 
 * @author Juan
 */
public class Control{

    /**
     * Objeto VistaConsola que representa la interfaz de consola.
     */
    private final VistaConsola vista;
    
    /**
     * Objeto ArchivoPropiedades que contiene las propiedades de conexión.
     */
    private final ArchivoPropiedades properties;
    
    /**
     * Objeto ConnServerSocket que representa el servidor.
     */
    private final ConnServerSocket servidor;
    
    /**
     * Objeto ConnServerSocket que representa el servidor.
     */
    private final UsuarioDAO gestorUsuarios;
    
    /**
     * Objeto ServidorHilo que representa el hilo de comunicación con el cliente.
     */
    private ServidorHilo hiloHablando;

    /**
     * Constructor de la clase Control.
     * Inicializa los objetos necesarios y inicia el servidor.
     */
    public Control() {
        vista = new VistaConsola();
        servidor = new ConnServerSocket();
        properties = new ArchivoPropiedades(new FileChooser("Seleccione datos de conexion").getFile());
        gestorUsuarios = new UsuarioDAO(this);
        

        loopServidor();

    }

    /**
     * Método que inicia el bucle principal del servidor.
     * Acepta conexiones de clientes y autentica los usuarios.
     */
    public void loopServidor() {

        try {
            int puerto1 = Integer.parseInt(properties.getData("Puerto.1"));
            int puerto2 = Integer.parseInt(properties.getData("Puerto.2"));
            servidor.runServer(puerto1, puerto2);
            while (true) {

                // Lanza el mensaje cada que espera cliente
                vista.mostrarMensaje("Servidor >> Esperando cliente");

                ConnSocket tempcon = new ConnSocket();
                tempcon.aceptarClientes(servidor.getServ(), servidor.getServ2());
                ServidorHilo userThread;

                // Lee el usuario y contraseña que le llega de vista
                String usuario = tempcon.getEntrada().readUTF();
                String contraseña = tempcon.getEntrada().readUTF();
                
                // Usa el modelo para guardar datos en un Usuario
                UsuarioVO user = new UsuarioVO();
                user.setUsuario(usuario);
                user.setContraseña(contraseña);
                
                // LLama al DAO para logear
                if (gestorUsuarios.consultaUsuario(user)) {
                    vista.mostrarMensajeEmergente("Servidor >> Conexion recibida");
                    tempcon.getSalida().writeInt(1);
                    userThread = new ServidorHilo(this, tempcon, usuario);
                    
                    userThread.start();
                    userThread.getConexionCliente().getSalida().writeInt(1);
                    userThread.getConexionCliente().getSalida().writeUTF("El cliente esta registrado");
                    
                } else {
                    
                    // Si se obtiene que no existe el usuario, se avisa
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

    /**
     * Método que obtiene el objeto VistaConsola.
     *
     * @return El objeto VistaConsola.
     */
    public VistaConsola getVista() {
        return vista;
    }

    /**
     * Método que obtiene el objeto ArchivoPropiedades.
     *
     * @return El objeto ArchivoPropiedades.
     */
    public ArchivoPropiedades getProperties() {
        return properties;
    }

    /**
     * Método que obtiene el objeto ServidorHilo que está leyendo por voz.
     *
     * @return El objeto ServidorHilo.
     */
    public ServidorHilo getHiloHablando() {
        return hiloHablando;
    }

     /**
     * Método que establece el objeto ServidorHilo que está leyendo por voz.
     *
     * @param hiloHablando El objeto ServidorHilo.
     */
    public void setHiloHablando(ServidorHilo hiloHablando) {
        this.hiloHablando = hiloHablando;
    }
}
