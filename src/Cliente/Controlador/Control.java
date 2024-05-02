/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cliente.Controlador;

import Cliente.Modelo.ConnCliente;
import Cliente.Vista.VentanaPrincipal;
import Cliente.Vista.FileChooser;
import Cliente.Modelo.ArchivoPropiedades;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Juan
 */
public class Control implements ActionListener{

    private ArchivoPropiedades puertos;
    private VentanaPrincipal vista;
    private UsuarioHilo usuario;
   
    public Control() {

        puertos = new ArchivoPropiedades(new FileChooser("Seleccione datos de conexion").getFile());
        vista = new VentanaPrincipal();
        ConnCliente.IP_SERVER = vista.inputEmergente("Ingrese direccion IP", "localhost");
        
        usuario = new UsuarioHilo(this);

        int puerto1 = Integer.parseInt(puertos.getData("Puerto.1"));
        int puerto2 = Integer.parseInt(puertos.getData("Puerto.2"));
    
        usuario.iniciarCliente(puerto1, puerto2);
        
        
        String username = vista.inputEmergente("Escriba su nombre de usuario");
        
        String password = vista.inputEmergente("Escriba su contraseña");

        usuario.inicioSesion(username, password);
        
        vista.setLocationRelativeTo(null);
        vista.btnLeer.addActionListener(this);
        vista.btnSalir.addActionListener(this);
        vista.setDefaultCloseOperation(0);
        vista.setVisible(true);
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {

        
        switch(e.getActionCommand())        
        {
            
            case("Leer")->{
        
                enviarMensajeServer();
                
            }
            case("Salir")->{
                vista.mensajeEmergente("Cerrando conexion");
                cerrarPrograma();
            }
            
            
        }
       
        
    }
    
    
    public void enviarMensajeServer(){
        try {
            usuario.getConexion().getSalida().writeInt(1);
            usuario.getConexion().getSalida().writeUTF(getVista().txAreaLeer.getText());
        } catch (IOException ex) {
 
        }
    }
    
    public void cerrarPrograma(){
        try {
            usuario.getConexion().getSalida().writeInt(2);
            System.exit(0);
        } catch (IOException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    
    public VentanaPrincipal getVista() {
        return vista;
    }
    
}
