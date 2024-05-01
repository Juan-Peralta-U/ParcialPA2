/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cliente.Controlador;

import Cliente.Modelo.ConnCliente;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Juan
 */
public class UsuarioHilo extends Thread{

    private Control control;
    private ConnCliente conexion;
    
    private int opcion = 0;
    
    public UsuarioHilo(Control control) {
        this.control = control;
        
        this.conexion = new ConnCliente(control);
    }


    public void inicioSesion(String username, String password){
         
        try {
            
            conexion.getSalida().writeUTF(username);
             
            conexion.getSalida().writeUTF(password);    
                
            int respuestaServer = conexion.getEntrada().readInt();
            
            if(respuestaServer == 1){ //true or false evaluation
                
                start();
                
            }
            else if (respuestaServer == 0){
                
                control.getVista().mensajeEmergente(conexion.getEntrada().readUTF());
                System.exit(0);
                
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    
    
    @Override
    public void run() {

        while (true) {
            
            try {
                switch (opcion) {
                    case 1: 
                        
                        break;
                    
                    case 2:
                        conexion.getSalida().writeInt(opcion);
                        System.exit(0);

                        break;
                        
                }
            } catch (IOException e) {
                // Se captura cuando la conexion se cae o se cierra con cerrar()
                control.getVista().mensajeConsola("Se corto la comunicacion");
                break;
            }
            
        opcion = 0;    
            
        }
        
        System.exit(0);
        
    }

    public void setOpcion(int opcion) {
        this.opcion = opcion;
    }

    
    public void iniciarCliente(int puerto1, int puerto2){
        try {
            conexion.conexion(puerto1, puerto2);
            
        } catch (IOException ex) {
            control.getVista().mensajeEmergente("No se pudo conectar al servidor");
            control.getVista().mensajeConsola(ex.getMessage() + " <----");
            System.exit(0);
        } catch (NumberFormatException ex) {
            control.getVista().mensajeEmergente("Los puertos especificados no son validos");
            control.getVista().mensajeConsola(ex.getMessage() + " <----");
            System.exit(0);
        }

    }

    public ConnCliente getConexion() {
        return conexion;
    }
    
    
    
    
    
}



    
    
    
    

