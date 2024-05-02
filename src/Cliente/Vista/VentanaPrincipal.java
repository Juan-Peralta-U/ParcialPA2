/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Cliente.Vista;

import java.awt.Color;
import javax.swing.JOptionPane;

/**
 *
 * @author cesar
 */
public class VentanaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form VistaPrincipal
     */
    public VentanaPrincipal() {
        initComponents();
        getContentPane().setBackground(Color.WHITE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labPortada = new javax.swing.JLabel();
        labelLeer = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txAreaLeer = new javax.swing.JTextArea();
        btnSalir = new javax.swing.JButton();
        btnLeer = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        labPortada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LectoText.png"))); // NOI18N
        labPortada.setText("imagen");
        labPortada.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));

        labelLeer.setFont(new java.awt.Font("Dubai Medium", 0, 18)); // NOI18N
        labelLeer.setText("Texto a leer:");

        txAreaLeer.setColumns(20);
        txAreaLeer.setFont(new java.awt.Font("Leelawadee UI", 0, 14)); // NOI18N
        txAreaLeer.setLineWrap(true);
        txAreaLeer.setRows(5);
        jScrollPane1.setViewportView(txAreaLeer);

        btnSalir.setBackground(new java.awt.Color(72, 70, 255));
        btnSalir.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        btnSalir.setForeground(new java.awt.Color(255, 255, 255));
        btnSalir.setText("Salir");

        btnLeer.setBackground(new java.awt.Color(72, 70, 255));
        btnLeer.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        btnLeer.setForeground(new java.awt.Color(255, 255, 255));
        btnLeer.setText("Leer");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(labelLeer, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(163, 163, 163)
                        .addComponent(btnLeer, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(157, 157, 157)
                        .addComponent(labPortada, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(91, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(labPortada, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(labelLeer, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLeer, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(57, 57, 57))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Método que lanza mensaje emergente
     * @param msj Mensaje a mostrar
     */
    public void mensajeEmergente(String msj){
        JOptionPane.showMessageDialog(null, msj);
    }
    
    /**
     * Método que obtiene un input por medio de ventana emergente
     * @param msj Mensaje a mostrar
     * @return Input ingresado
     */
    public String inputEmergente(String msj){
        return JOptionPane.showInputDialog(null,msj);
    }
        
        
    /**
     * Muestra un cuadro de diálogo de entrada con un valor por defecto utilizando JOptionPane.
     *
     * @param msj  Mensaje a mostrar en el cuadro de diálogo.
     * @param msj2 Valor por defecto para el campo de entrada.
     * @return La cadena ingresada por el usuario, o null si se canceló la operación.
     */
    public String inputEmergente(String msj, String msj2){
        return JOptionPane.showInputDialog(msj, msj2);
    }
    
    public void mensajeConsola(String msj){
        System.out.println(msj);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnLeer;
    public javax.swing.JButton btnSalir;
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JLabel labPortada;
    public javax.swing.JLabel labelLeer;
    public javax.swing.JTextArea txAreaLeer;
    // End of variables declaration//GEN-END:variables
}
