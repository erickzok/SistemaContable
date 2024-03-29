/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Visual;

import Tabla.imprimirTabla;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTable;
import org.apache.poi.ss.usermodel.Workbook;


import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author Erick
 */
public class tablaUsuarios extends javax.swing.JFrame {
    imprimirTabla x= new imprimirTabla();
    /**
     * Creates new form tablaUsuarios
     */
    public tablaUsuarios() {
        initComponents();
        setLocationRelativeTo(null);

        x.mostrarUsuariosEnJTable(TablaUsuarios);
    }
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Visual/iconACTIVIDAD.png"));
        return retValue;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        TablaUsuarios = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());

        TablaUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        TablaUsuarios.setEnabled(false);
        TablaUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaUsuariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TablaUsuarios);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Usuarios Registrados:");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jButton3.setText("Menú");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });

        jButton4.setText("Actualizar");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 521, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addGap(108, 108, 108)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3)))
                .addGap(22, 22, 22))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TablaUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaUsuariosMouseClicked
        // TODO add your handling code here:
              
        if (evt.getClickCount() == 2) {
            int column = TablaUsuarios.getColumnModel().getColumnIndexAtX(evt.getX());
            int row = evt.getY() / TablaUsuarios.getRowHeight();

            if (row < TablaUsuarios.getRowCount() && row >= 0 && column < TablaUsuarios.getColumnCount() && column >= 0) {
                String Stringid = String.valueOf(TablaUsuarios.getValueAt(row, 0));
                int id= Integer.parseInt(Stringid);
                // Crear una instancia de InfoDato
                VerDetalleUsuario DatosPanel = new VerDetalleUsuario();
                DatosPanel.setIdUsuario(id);
                //DatosPanel.setDatosPersonaProyectos(id);
                
                // Mostrar la ventana emergente
                DatosPanel.setLocationRelativeTo(null);
                DatosPanel.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cerrar solo esta ventana al cerrar
                DatosPanel.setVisible(true);
            }
        }
    }//GEN-LAST:event_TablaUsuariosMouseClicked

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        // TODO add your handling code here:
        
        MenuPrivado1 AE = new MenuPrivado1 ();

        AE.setVisible(true);

        dispose();
    }//GEN-LAST:event_jButton3MouseClicked

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
        // TODO add your handling code here:
        tablaUsuarios EP = new tablaUsuarios ();
        EP.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton4MouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
         try {
        exportarExcel(TablaUsuarios);
    } catch (IOException ex) {
        ex.printStackTrace();
        // Mostrar mensaje de error al usuario
        JOptionPane.showMessageDialog(this, "Error al exportar a Excel: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_jLabel1MouseClicked

 public void exportarExcel(JTable tabla) throws IOException {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de Excel (*.xls)", "xls");
        chooser.setFileFilter(filter);
        chooser.setDialogTitle("Guardar archivo");
        chooser.setAcceptAllFileFilterUsed(false);

        if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            String ruta = chooser.getSelectedFile().toString().concat(".xls");

            try {
                File archivoXLS = new File(ruta);
                if (archivoXLS.exists()) {
                    archivoXLS.delete();
                }
                archivoXLS.createNewFile();

                Workbook libro = new HSSFWorkbook();
                FileOutputStream archivo = new FileOutputStream(archivoXLS);
                Sheet hoja = libro.createSheet("Mi hoja de trabajo 1");
                hoja.setDisplayGridlines(false);

                // Encabezados
                Row headerRow = hoja.createRow(0);
                for (int c = 0; c < tabla.getColumnCount(); c++) {
                    Cell celda = headerRow.createCell(c);
                    celda.setCellValue(tabla.getColumnName(c));
                }

                // Datos
                for (int f = 0; f < tabla.getRowCount(); f++) {
                    Row fila = hoja.createRow(f + 1);
                    for (int c = 0; c < tabla.getColumnCount(); c++) {
                        Cell celda = fila.createCell(c);
                        if (tabla.getValueAt(f, c) instanceof Double) {
                            celda.setCellValue(Double.parseDouble(tabla.getValueAt(f, c).toString()));
                        } else if (tabla.getValueAt(f, c) instanceof Float) {
                            celda.setCellValue(Float.parseFloat(tabla.getValueAt(f, c).toString()));
                        } else {
                            celda.setCellValue(String.valueOf(tabla.getValueAt(f, c)));
                        }
                    }
                }

                libro.write(archivo);
                archivo.close();

                Desktop.getDesktop().open(archivoXLS);

            } catch (IOException | NumberFormatException e) {
                throw e;
            }
        }
    } 
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(tablaUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(tablaUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(tablaUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(tablaUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new tablaUsuarios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaUsuarios;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
