/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cliente.Controlador;

import Cliente.Controlador.UsuarioHilo;
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
    private ConnCliente conexion;
    private VentanaPrincipal vista;
    private UsuarioHilo usuario;
   
    public Control() {
        
        vista = new VentanaPrincipal();

        puertos = new ArchivoPropiedades(new FileChooser("Selecione Puertos").getFile());
        ConnCliente.IP_SERVER = vista.inputEmergente("Seleccione Direccion IP", "localhost");
        
        usuario = new UsuarioHilo(this);

        int puerto1 = Integer.parseInt(puertos.getData("Puerto.1"));
        int puerto2 = Integer.parseInt(puertos.getData("Puerto.2"));
    
        usuario.iniciarCliente(puerto1, puerto2);
        
        String username = vista.inputEmergente("Escriba su nombre de usuario");
        
        String password = vista.inputEmergente("Escriba su contraseña");

        usuario.inicioSesion(username, password);
        
        vista.btnLeer.addActionListener(this);
        vista.btnSalir.addActionListener(this);
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
                cerrarPrograma();
            }
            
            
        }
       
        
    }
    
    
    public void enviarMensajeServer(){
        try {
            usuario.getConexion().getSalida().writeInt(1);
            usuario.getConexion().getSalida().writeUTF(getVista().txAreaLeer.getText());
        } catch (IOException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
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
