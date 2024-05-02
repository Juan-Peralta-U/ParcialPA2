/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servidor.Modelo;

/**
 * Clase UsuarioVO que representa un objeto de usuario con su nombre de usuario y contraseña.
 * 
 * @author Familia Mora
 */
public class UsuarioVO {
    
    /**
     * El nombre de usuario.
     */
    private String usuario;
    
    /**
     * La contraseña del usuario.
     */
    private String contraseña;

    /**
     * Constructor vacío de la clase UsuarioVO.
     */
    public UsuarioVO() {
    }

    /**
     * Obtiene el nombre de usuario.
     *
     * @return El nombre de usuario.
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Establece el nombre de usuario.
     *
     * @param usuario El nombre de usuario.
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return La contraseña del usuario.
     */
    public String getContraseña() {
        return contraseña;
    }

    /**
     * Establece la contraseña del usuario.
     *
     * @param contraseña La contraseña del usuario.
     */
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
   
}
