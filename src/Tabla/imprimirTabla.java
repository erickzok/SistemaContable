/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tabla;

import DAO.tablaDAO;
import Entidad.Actividad;
import Entidad.Empleado;
import java.awt.Component;
import java.util.ArrayList;
import java.sql.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/**
 *
 * @author ADMIN
 */
public class imprimirTabla {
    tablaDAO listaVO = new tablaDAO ();
    public void visualizar_tabla_Actividades (JTable tabla, int idEmpleado) {
    String[] nombreColumnas = {"ID Actividad", "Nombre del Profesional","Hora", "Proyecto", "JefeInmediato", "Actividad especifica", "Porcentaje", "Fecha Registro","Fecha Fin"};
    DefaultTableModel modelo = new DefaultTableModel(null, nombreColumnas) {
        @Override
        public Class<?> getColumnClass(int columnIndex) {
            if (columnIndex == 0 ) {
                return Integer.class; // Las primeras dos columnas (idActividad y NombreEmpleado) serán de tipo Integer
            } else if (columnIndex == 7 || columnIndex==6) {
                return Date.class; // La columna FechaFin será de tipo Date
            } else {
                return String.class; // Las demás columnas serán de tipo String
            }
        }
    };


    Actividad vo = new Actividad();
    ArrayList<Actividad> list = listaVO.ListarActividadesEmpleado(idEmpleado);
    

    if (list.size() > 0) {
        for (int i = 0; i < list.size(); i++) {
            vo = list.get(i);
            Object fila[] = new Object[] {
                
                vo.getIdActividad(),
                vo.getNombreEmpleado(),
                vo.getHora(),
                vo.getProyecto(),
                
                vo.getJefe(),
                vo.getActividad(),
                vo.getPorcentajeAvance(),
                vo.getFechaRegistro(),
                vo.getFechaFinEstimada(),
                
                
            };

            modelo.addRow(fila);
        }
        
            tabla.setModel(modelo);
            tabla.setRowHeight(32);
        
        
    }
    else {
            JOptionPane.showMessageDialog(null, "¡Aviso: no Hay Actividades registradas", "Error", JOptionPane.ERROR_MESSAGE);

        }
        
   }
    
    
    public void visualizar_tabla_Actividades_Total(JTable tabla) {
    String[] nombreColumnas = {"ID Actividad", "Nombre del Profesional", "Proyecto", "JefeInmediato", "Actividad especifica", "Porcentaje", "Fecha Registro","Fecha Fin"};
    DefaultTableModel modelo = new DefaultTableModel(null, nombreColumnas) {
        @Override
        public Class<?> getColumnClass(int columnIndex) {
            if (columnIndex == 0 ) {
                return Integer.class; // Las primeras dos columnas (idActividad y NombreEmpleado) serán de tipo Integer
            } else if (columnIndex == 7 || columnIndex==6) {
                return Date.class; // La columna FechaFin será de tipo Date
            } else {
                return String.class; // Las demás columnas serán de tipo String
            }
        }
    };

    Actividad vo = new Actividad();
    ArrayList<Actividad> list = listaVO.ListarActividadesTotal();

    if (list.size() > 0) {
        for (int i = 0; i < list.size(); i++) {
            vo = list.get(i);
            Object fila[] = new Object[] {
                vo.getIdActividad(),
                vo.getNombreEmpleado(),
                vo.getProyecto(),
                vo.getJefe(),
                vo.getActividad(),
                vo.getPorcentajeAvance(),
                vo.getFechaRegistro(),
                vo.getFechaFinEstimada()
                
            };

            modelo.addRow(fila);
        }

        tabla.setModel(modelo);
        tabla.setRowHeight(32);

        // Ajustar automáticamente el tamaño de las columnas al contenido
        for (int i = 0; i < tabla.getColumnCount(); i++) {
            TableColumn column = tabla.getColumnModel().getColumn(i);
            TableCellRenderer renderer = column.getHeaderRenderer();
            if (renderer == null) {
                renderer = tabla.getTableHeader().getDefaultRenderer();
            }
            Component comp = renderer.getTableCellRendererComponent(tabla, column.getHeaderValue(), false, false, 0, 0);
            int width = comp.getPreferredSize().width;
            for (int row = 0; row < tabla.getRowCount(); row++) {
                TableCellRenderer cellRenderer = tabla.getCellRenderer(row, i);
                Component c = tabla.prepareRenderer(cellRenderer, row, i);
                width = Math.max(c.getPreferredSize().width, width);
            }
            column.setPreferredWidth(width + 10); // Agrega un pequeño margen
        }
    } else {
        JOptionPane.showMessageDialog(null, "¡Aviso: no hay Actividades registradas", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    public void visualizar_tabla_Filtrada_Actividades (JTable tabla, Integer idEmpleado , Date fecha) {
    String[] nombreColumnas = {"ID Actividad", "Nombre del Profesional", "Proyecto", "JefeInmediato", "Actividad especifica", "Porcentaje", "Fecha Registro","Fecha Fin"};
   DefaultTableModel modelo = new DefaultTableModel(null, nombreColumnas) {
        @Override
        public Class<?> getColumnClass(int columnIndex) {
            if (columnIndex == 0 ) {
                return Integer.class; // Las primeras dos columnas (idActividad y NombreEmpleado) serán de tipo Integer
            } else if (columnIndex == 7 || columnIndex==6) {
                return Date.class; // La columna FechaFin será de tipo Date
            } else {
                return String.class; // Las demás columnas serán de tipo String
            }
        }
    };


    Actividad vo = new Actividad();
    ArrayList<Actividad> list = listaVO.ListarActividadesPorEmpleadoYFecha(idEmpleado , fecha);
    

    if (list.size() > 0) {
        for (int i = 0; i < list.size(); i++) {
            vo = list.get(i);
            Object fila[] = new Object[] {
                
                vo.getIdActividad(),
                vo.getNombreEmpleado(),
                vo.getProyecto(),
                vo.getJefe(),
                vo.getActividad(),
                vo.getPorcentajeAvance(),
                vo.getFechaRegistro(),
                vo.getFechaFinEstimada()
                
                
            };

            modelo.addRow(fila);
        }
        
            tabla.setModel(modelo);
            tabla.setRowHeight(32);
        for (int i = 0; i < tabla.getColumnCount(); i++) {
            TableColumn column = tabla.getColumnModel().getColumn(i);
            TableCellRenderer renderer = column.getHeaderRenderer();
            if (renderer == null) {
                renderer = tabla.getTableHeader().getDefaultRenderer();
            }
            Component comp = renderer.getTableCellRendererComponent(tabla, column.getHeaderValue(), false, false, 0, 0);
            int width = comp.getPreferredSize().width;
            for (int row = 0; row < tabla.getRowCount(); row++) {
                TableCellRenderer cellRenderer = tabla.getCellRenderer(row, i);
                Component c = tabla.prepareRenderer(cellRenderer, row, i);
                width = Math.max(c.getPreferredSize().width, width);
            }
            column.setPreferredWidth(width + 10); // Agrega un pequeño margen
        }
        
    }
    else {
            JOptionPane.showMessageDialog(null, "¡Aviso: no Hay Actividades registradas", "Error", JOptionPane.ERROR_MESSAGE);

        }
        
   }

  public void visualizar_tabla_Filtrada_Actividades_Empleado (JTable tabla, Integer idEmpleado) {
    String[] nombreColumnas = {"ID Actividad", "Nombre del Profesional", "Proyecto", "JefeInmediato", "Actividad especifica", "Porcentaje", "Fecha Registro","Fecha Fin"};
   DefaultTableModel modelo = new DefaultTableModel(null, nombreColumnas) {
        @Override
        public Class<?> getColumnClass(int columnIndex) {
            if (columnIndex == 0 ) {
                return Integer.class; // Las primeras dos columnas (idActividad y NombreEmpleado) serán de tipo Integer
            } else if (columnIndex == 7 || columnIndex==6) {
                return Date.class; // La columna FechaFin será de tipo Date
            } else {
                return String.class; // Las demás columnas serán de tipo String
            }
        }
    };


    Actividad vo = new Actividad();
    ArrayList<Actividad> list = listaVO.ListarActividadesPorEmpleado(idEmpleado );
    

    if (list.size() > 0) {
        for (int i = 0; i < list.size(); i++) {
            vo = list.get(i);
            Object fila[] = new Object[] {
                
                vo.getIdActividad(),
                vo.getNombreEmpleado(),
                vo.getProyecto(),
                vo.getJefe(),
                vo.getActividad(),
                vo.getPorcentajeAvance(),
                vo.getFechaRegistro(),
                vo.getFechaFinEstimada()
                
                
            };

            modelo.addRow(fila);
        }
        
            tabla.setModel(modelo);
            tabla.setRowHeight(32);
        for (int i = 0; i < tabla.getColumnCount(); i++) {
            TableColumn column = tabla.getColumnModel().getColumn(i);
            TableCellRenderer renderer = column.getHeaderRenderer();
            if (renderer == null) {
                renderer = tabla.getTableHeader().getDefaultRenderer();
            }
            Component comp = renderer.getTableCellRendererComponent(tabla, column.getHeaderValue(), false, false, 0, 0);
            int width = comp.getPreferredSize().width;
            for (int row = 0; row < tabla.getRowCount(); row++) {
                TableCellRenderer cellRenderer = tabla.getCellRenderer(row, i);
                Component c = tabla.prepareRenderer(cellRenderer, row, i);
                width = Math.max(c.getPreferredSize().width, width);
            }
            column.setPreferredWidth(width + 10); // Agrega un pequeño margen
        }
        
    }
    else {
            JOptionPane.showMessageDialog(null, "¡Aviso: no Hay Actividades registradas", "Error", JOptionPane.ERROR_MESSAGE);

        }
        
   }
  public void visualizar_tabla_Filtrada_Actividades_Fecha (JTable tabla , Date fecha) {
    String[] nombreColumnas = {"ID Actividad", "Nombre del Profesional", "Proyecto", "JefeInmediato", "Actividad especifica", "Porcentaje", "Fecha Registro","Fecha Fin"};
   DefaultTableModel modelo = new DefaultTableModel(null, nombreColumnas) {
        @Override
        public Class<?> getColumnClass(int columnIndex) {
            if (columnIndex == 0 ) {
                return Integer.class; // Las primeras dos columnas (idActividad y NombreEmpleado) serán de tipo Integer
            } else if (columnIndex == 7 || columnIndex==6) {
                return Date.class; // La columna FechaFin será de tipo Date
            } else {
                return String.class; // Las demás columnas serán de tipo String
            }
        }
    };


    Actividad vo = new Actividad();
    ArrayList<Actividad> list = listaVO.ListarActividadesPorFecha( fecha);
    

    if (list.size() > 0) {
        for (int i = 0; i < list.size(); i++) {
            vo = list.get(i);
            Object fila[] = new Object[] {
                
                vo.getIdActividad(),
                vo.getNombreEmpleado(),
                vo.getProyecto(),
                vo.getJefe(),
                vo.getActividad(),
                vo.getPorcentajeAvance(),
                vo.getFechaRegistro(),
                vo.getFechaFinEstimada()
                
                
            };

            modelo.addRow(fila);
        }
        
            tabla.setModel(modelo);
            tabla.setRowHeight(32);
        for (int i = 0; i < tabla.getColumnCount(); i++) {
            TableColumn column = tabla.getColumnModel().getColumn(i);
            TableCellRenderer renderer = column.getHeaderRenderer();
            if (renderer == null) {
                renderer = tabla.getTableHeader().getDefaultRenderer();
            }
            Component comp = renderer.getTableCellRendererComponent(tabla, column.getHeaderValue(), false, false, 0, 0);
            int width = comp.getPreferredSize().width;
            for (int row = 0; row < tabla.getRowCount(); row++) {
                TableCellRenderer cellRenderer = tabla.getCellRenderer(row, i);
                Component c = tabla.prepareRenderer(cellRenderer, row, i);
                width = Math.max(c.getPreferredSize().width, width);
            }
            column.setPreferredWidth(width + 10); // Agrega un pequeño margen
        }
        
    }
    else {
            JOptionPane.showMessageDialog(null, "¡Aviso: no Hay Actividades registradas", "Error", JOptionPane.ERROR_MESSAGE);

        }
        
   }

public void mostrarUsuariosEnJTable(JTable table) {
    tablaDAO tablaDAO = new tablaDAO();
    DefaultTableModel model = new DefaultTableModel();
    table.setModel(model);

    // Obtener los datos directamente del método listarUsuarios
    ArrayList<String[]> data = tablaDAO.listarUsuarios();
    if (!data.isEmpty()) {
        model.addColumn("Id");

        model.addColumn("Nombre Profesional");
        model.addColumn("Usuario");
        model.addColumn("Contraseña");
    }
    // Agregar las columnas al modelo (solo si hay datos)
    

    // Agregar los datos al modelo
    for (String[] row : data) {
        model.addRow(row);
    }
}

}



