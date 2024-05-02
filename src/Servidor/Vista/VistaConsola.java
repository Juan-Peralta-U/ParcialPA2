/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servidor.Vista;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Clase VistaConsola que maneja la visualización de mensajes en la consola y ventanas emergentes.
 * @author cesar
 */
public class VistaConsola{

    /**
     * Constructor de la clase VistaConsola.
     */
    public VistaConsola() {
    }
    
    /**
     * Muestra un mensaje en la consola.
     *
     * @param msj El mensaje a mostrar.
     */
    public void mostrarMensaje(String msj){
        System.out.println(msj);
    }
    
    /**
     * Muestra un mensaje en una ventana emergente.
     *
     * @param msj El mensaje a mostrar.
     */
    public void mostrarMensajeEmergente(String msj){
        JOptionPane.showMessageDialog(null, msj);
    }
    
}
