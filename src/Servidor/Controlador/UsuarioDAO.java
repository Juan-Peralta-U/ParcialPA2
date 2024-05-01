/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servidor.Controlador;

import Servidor.Modelo.Conexion.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Familia Mora
 */
public class UsuarioDAO {

    private Connection con;
    /**
     * atributo traductor con el lenguaje SQL de una sentencia DML
     */
    private Statement st;
    /**
     * Conjunto de resultados que se devuelven de una query
     */
    private ResultSet rs;

    /**
     * Constructor de la clase UsuarioDAO, inicializa los atributos en null
     */
    public UsuarioDAO() {
        con = null;
        st = null;
        rs = null;
    }

    /*
     * Metodo consultaUsuario, a partir de la conexion con la base de datos, usamos
     * sentencia SQL que la traducira st para poder ejecutar su funcion que sera
     * mostrar todos los campos de la tabla Usuario en la base de datos que
     * contengan el nombre y contraseña que definimos
     *
     * @param usuario recibe el campo de la base de datos donde se va a consultar
     * @param contraseña recibe el otro campo que se desea consultar
     * @return retorna el resultado de la comparacion
     */
    public boolean consultaUsuario(String usuario, String contraseña) {

        try {
            con = Conexion.getConexion();
            st = con.createStatement();
            String consulta = "SELECT * FROM Usuario WHERE nombreUsuario='" + usuario + "' AND contraseña='" + contraseña + "'";
            rs = st.executeQuery(consulta);

            // Verificar si hay algún resultado
            if(rs.next()) {
                // Si hay al menos un resultado, el usuario existe
                rs.close();
                st.close();
                Conexion.desconectar();
                return true;
            } else {
                // Si no hay resultados, el usuario no existe
                rs.close();
                st.close();
                Conexion.desconectar();
                return false;
            }
        } catch (Exception e) {
            return false; // En caso de excepción, devolver falso
        }
    }

}
