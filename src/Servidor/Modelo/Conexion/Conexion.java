/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servidor.Modelo.Conexion;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

/**
 * Clase conexión contiene los datos de la conexion con la DB
 * @author Familia Mora
 */
public class Conexion {
    /**
     * Es la conexion a la base de datos
     */
    private static Connection cn = null;

    /**
     * Se conecta con la base de datos
     * @return retorna la conexion con la base de datos
     */
    public static Connection getConexion(String URLBD, String usuario, String contrasena) {
        try {
            cn = DriverManager.getConnection(URLBD, usuario, contrasena);
        } catch (SQLException ex) {
            
        }
        return cn;
    }
    
    /**
     * Se desconecta de la base de datos
     */
    public static void desconectar() {
        cn = null;
    }


    
}