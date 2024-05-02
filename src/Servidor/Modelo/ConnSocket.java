/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servidor.Modelo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase ConnSocket que maneja la conexión para cada cliente.
 *
 * @author cesar
 */
public class ConnSocket {

    /**
     * Objeto Socket que representa la conexión entrante del cliente.
     */
    private Socket sock = null;

    /**
     * Objeto Socket que representa la conexión saliente hacia el cliente.
     */
    private Socket sock2 = null;

    /**
     * Objeto DataInputStream que lee datos de entrada del cliente.
     */
    private DataInputStream entrada = null;

    /**
     * Objeto DataOutputStream que envía datos de salida al cliente (conexión
     * entrante).
     */
    private DataOutputStream salida = null;

    /**
     * Objeto DataOutputStream que envía datos de salida al cliente (conexión
     * saliente).
     */
    private DataOutputStream salida2 = null;

    /**
     * Constructor de la clase ConnSocket.
     */
    public ConnSocket() {
    }

    /**
     * Esta funcion crea la conexion unica entre el servidor y el cliente.
     * Acepta al cliente y seguidamente crea sus stream tanto de entrada como de
     * salida
     *
     * @param serv recibe sockect servidor entrada
     * @param serv2 recibe sockecte servido salida
     * @throws IOException
     */
    public void aceptarClientes(ServerSocket serv, ServerSocket serv2) throws IOException {
        sock = serv.accept();
        sock2 = serv2.accept();

        entrada = new DataInputStream(sock.getInputStream());
        salida = new DataOutputStream(sock.getOutputStream());
        salida2 = new DataOutputStream(sock2.getOutputStream());
    }

    /**
     * Método que cierra las conexiones con el cliente.
     */
    public void cerrar() {
        try {
            sock.close();
            sock2.close();
        } catch (IOException ex) {

        }
    }

    /**
     * Obtiene el objeto Socket que representa la conexión entrante del cliente.
     *
     * @return El objeto Socket sock.
     */
    public Socket getSock() {
        return sock;
    }

    /**
     * Obtiene el objeto Socket que representa la conexión saliente hacia el
     * cliente.
     *
     * @return El objeto Socket sock2.
     */
    public Socket getSock2() {
        return sock2;
    }

    /**
     * Obtiene el objeto DataInputStream que lee datos de entrada del cliente.
     *
     * @return El objeto DataInputStream entrada.
     */
    public DataInputStream getEntrada() {
        return entrada;
    }

    /**
     * Obtiene el objeto DataOutputStream que envía datos de salida al cliente
     * (conexión entrante).
     *
     * @return El objeto DataOutputStream salida.
     */
    public DataOutputStream getSalida() {
        return salida;
    }

    /**
     * Obtiene el objeto DataOutputStream que envía datos de salida al cliente
     * (conexión saliente).
     *
     * @return El objeto DataOutputStream salida2.
     */
    public DataOutputStream getSalida2() {
        return salida2;
    }

}
