/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servidor.Modelo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

/**
 * Clase coneccion contiene los datos de la conexion con la DB
 * @author Familia Mora
 */
public class Conexion {
    /**
     * es la conexion a la base de datos
     */
    private static Connection cn = null;
    //private static String URLBD = "jdbc:mysql://localhost:3308/Parcial2";
    /**
     * es la url de conexion a la base datos (conector, Motor BD, Ubicacion, nombre BD)
    */
    private static String URLBD = "jdbc:mysql://localhost:3306/parcial2";
    /**
     * usuario de la base de datos
     */
    private static String usuario = "root";
    /**
     * Contraseña de la base de datos
     */
    private static String contrasena = "";

    /**
     * Se conecta con la base de datos
     * @return retorna la conexion con la base de datos
     */
    public static Connection getConexion() {
        try {
            cn = DriverManager.getConnection(URLBD, usuario, contrasena);
        } catch (SQLException ex) {
            //System.out.println("No se puede cargar el controlador");
        }
        return cn;
    }
    
    /**
     * se desconecta de la base de datos
     */
    public static void desconectar() {
        cn = null;
    }
}