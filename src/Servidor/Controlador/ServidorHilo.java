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
public class ServidorHilo extends Thread {
   
   private Control control; 
   private GestorLectura leer;
   private String nombreUsuario;
   private final ConnSocket conexionCliente;
   
   public ServidorHilo(Control control, ConnSocket conexionCliente, String nombreUsuario) {
        this.conexionCliente = conexionCliente;
        this.control = control;
        this.nombreUsuario = nombreUsuario;

        leerMsj("Bienvenido " + nombreUsuario);
   }       

   @Override
   public void run(){

        manejoAcciones();
        
        try {
            conexionCliente.cerrar();
            control.getVista().mostrarMensaje("Se desconecto un usuario");
        } catch (Exception et) {
            control.getVista().mostrarMensaje("No se puede cerrar el socket");
        }
    }
   
   
   private void manejoAcciones(){
       while(true){
           try{
              int opcion = conexionCliente.getEntrada().readInt();
              
            switch(opcion){
                
                case 1->{
                    String msj = conexionCliente.getEntrada().readUTF();
                    leerMsj(msj);
                }
                case 2->{
                    leerMsj("Hasta luego " + nombreUsuario);
                    
                }
                
            }
               
           }
           catch (IOException e) {
                control.getVista().mostrarMensaje("El cliente termino la conexion");
                break;
            }
       }
   }
   
   
   
   private void leerMsj(String msj){
       
       try {
           leer = new GestorLectura();
           leer.leer(msj);
           
       } catch (IllegalArgumentException ex) {
           Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
       } catch (EngineException ex) {
           Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
       } catch (AudioException ex) {
           Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
       } catch (InterruptedException ex) {
           Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
       }
       
       
   }
   
   
   
    
   
   
}
