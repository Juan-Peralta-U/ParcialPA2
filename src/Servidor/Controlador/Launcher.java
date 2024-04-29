/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Servidor.Controlador;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.speech.EngineException;

/**
 *
 * @author cesar
 */
public class Launcher {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            GestorLectura gestor = new GestorLectura();
            gestor.leer("hola");
            gestor.leer("como estan");
            gestor.cerrar();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
