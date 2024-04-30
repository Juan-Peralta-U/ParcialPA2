/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servidor.Vista;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author cesar
 */
public class VistaConsola{

    public VistaConsola() {
    }
    
    public void mostrarMensaje(String msj){
        System.out.println(msj);
    }
    
    public void mostrarMensajeEmergente(String msj){
        JOptionPane.showMessageDialog(null, msj);
    }
    
}
