/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servidor.Modelo;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Clase que declara lo correspondiente al ServerSocket que aceptará clientes
 * 
 * @author cesar
 */
public class ConnServerSocket{

    private ServerSocket serv = null; // Para comunicacion
    private ServerSocket serv2 = null; // Para enviar mensajes

    public ConnServerSocket() {
    }

    /**
     * Esta funcion solo crea el servidor y los puertos 
     * @param puerto1 recibe los puertos en los que va a escuchar 
     * @param puerto2 recibe puertos de salida 
     * @throws IOException 
     */    
    public void runServer(int puerto1, int puerto2) throws IOException {

        serv = new ServerSocket(puerto1);
        serv2 = new ServerSocket(puerto2);
        
    }

    /**
     * Método que obtiene el serversocket
     * @return El obketo ServerSocket
     */
    public ServerSocket getServ() {
        return serv;
    }

    /**
     * Método que obtiene el serversocket2
     * @return El objeto ServerSocket
     */
    public ServerSocket getServ2() {
        return serv2;
    }
    
    
    
}