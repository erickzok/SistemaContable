/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entidad.Actividad;
import Entidad.Conectar;
import Entidad.Empleado;
import Entidad.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.sql.Time;
import java.time.LocalDate;
import java.sql.ResultSetMetaData;

import java.sql.Date;
import java.time.LocalDate;
import javax.swing.JLabel;
import javax.swing.JTextField;
/**
 *
 * @author ADMIN
 */
public class tablaDAO {
    
    public int auto_increment(String sql) {
        int id = 1;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Conectar db = new Conectar();
        try {
            ps = db.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1) + 1;
            }
        } catch (Exception ex) {
            System.out.println("idmaximo" + ex.getMessage());
            id = 1;
        } finally {
            try {
                ps.close();
                rs.close();
                db.desconectar();
            } catch (Exception ex) {
            }
        }
        return id;
    }
    
    
    
    public void registrarActividadesEmpleado(Actividad actividad_x){
        Conectar conec = new Conectar();

        Date fechaActual = Date.valueOf(LocalDate.now());
        String sql = "INSERT INTO Actividad (idActividad, Proyecto, JefeInmediato, Actividad, Porcentaje, FechaFin, Comentarios, idEmpleado, Hora,FechaRegistro) VALUES (?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        
       
        try {
            

            ps = conec.getConnection().prepareStatement(sql);
            ps.setInt(1,actividad_x.getIdActividad());
            ps.setString(2,actividad_x.getProyecto());
            ps.setString(3,actividad_x.getJefe());
            ps.setString(4, actividad_x.getActividad());
            ps.setString(5, actividad_x.getPorcentajeAvance());
            ps.setDate(6, new java.sql.Date(actividad_x.getFechaFinEstimada().getTime()));
            ps.setString(7,actividad_x.getComentarios());
            ps.setInt(8, actividad_x.getIdEmpleado());
            ps.setTime(9, Time.valueOf(LocalTime.now()));
            ps.setDate(10, fechaActual);


            
            
            
            
                  
            ps.executeUpdate();
            System.out.println("Resgistro  actividad correcto");


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        
        }
         
    }
    
    
    
    public ArrayList<Actividad> ListarActividadesEmpleado(int IdEmpleado){
         ArrayList<Actividad> list = new ArrayList<>();
        Conectar conec = new Conectar();

        
        String sql = "SELECT E.NombreEmpleado, A.idActividad, A.Proyecto, A.JefeInmediato, A.Actividad, A.Porcentaje, A.FechaFin, A.Comentarios , A.hora , A.FechaRegistro FROM Actividad A JOIN Empleado E ON A.idEmpleado = E.idEmpleado WHERE E.idEmpleado = ?;";
        ResultSet rs = null;
    PreparedStatement ps = null;
        
       
        try {
            

            ps = conec.getConnection().prepareStatement(sql);
            ps.setInt(1,IdEmpleado);
           

        
            
            
            
            
            
                  
            rs = ps.executeQuery();
            while (rs.next()) {
             
            Actividad vo = new Actividad();
            vo.setNombreEmpleado(rs.getString(1));
            vo.setIdActividad(rs.getInt(2));
            vo.setProyecto(rs.getString(3));
            vo.setJefe(rs.getString(4));    
            vo.setActividad(rs.getString(5));
            vo.setPorcentajeAvance(rs.getString(6));
            vo.setIdEmpleado(IdEmpleado);
            vo.setFechaFinEstimada(rs.getDate(7));
            
            vo.setComentarios(rs.getString(8));
            vo.setHora(rs.getString(9));

            vo.setFechaRegistro(rs.getDate(10));
            list.add(vo);
            System.out.println("Terminóy");
        }


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        
        }finally {
            try {
                ps.close();
                rs.close();
                conec.desconectar();
            } catch (Exception ex) {
            }
        }
         
    
        return list;
     }
    
public Actividad obtenerDatosActividad(int idActividad) {
    Actividad actividadX = new Actividad();
    Conectar conec = new Conectar();
    String sql = "SELECT E.NombreEmpleado, A.idActividad, A.Proyecto, A.JefeInmediato, A.Actividad, A.Porcentaje, A.FechaFin, A.Comentarios, A.hora, A.FechaRegistro FROM Actividad A JOIN Empleado E ON A.idEmpleado = E.idEmpleado WHERE A.idActividad = ?";
    
    try (PreparedStatement ps = conec.getConnection().prepareStatement(sql)) {
        ps.setInt(1, idActividad);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                Actividad vo = new Actividad();
                vo.setNombreEmpleado(rs.getString(1));
                vo.setIdActividad(rs.getInt(2));
                vo.setProyecto(rs.getString(3));
                vo.setJefe(rs.getString(4));
                vo.setActividad(rs.getString(5));
                vo.setPorcentajeAvance(rs.getString(6));
                vo.setFechaFinEstimada(rs.getDate(7));
                vo.setComentarios(rs.getString(8));
                vo.setHora(rs.getString(9));
                vo.setFechaRegistro(rs.getDate(10));
                actividadX = vo;
                System.out.println("Terminó");
            }
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }finally {
            try {
                
                conec.desconectar();
            } catch (Exception ex) {
            }
        }
    
    return actividadX;
}

    
    
public boolean existeUsuario(String nombreUsuario) {
    Conectar conectar = new Conectar();
    String sql = "SELECT COUNT(*) FROM Usuario WHERE Usuario = ?";
    ResultSet rs = null;
    PreparedStatement ps = null;

    try {
        ps = conectar.getConnection().prepareStatement(sql);
        ps.setString(1, nombreUsuario);

        rs = ps.executeQuery();

        if (rs.next()) {
            int count = rs.getInt(1);
            return count > 0; // Devuelve true si el usuario existe, false si no
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    } finally {
        // Cerrar recursos en el bloque finally
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                ps.close();
                rs.close();
                conectar.desconectar();
            } catch (Exception ex) {
            }
        }
    }
    
    return false; // En caso de error, asumimos que el usuario no existe
}

    public String InicioUsuario(String user , String pass){
         ArrayList<Actividad> list = new ArrayList<>();
        Conectar conec = new Conectar();
        String idEmpleado = null;
        
        String sql = "SELECT idEmpleado FROM Usuario WHERE usuario = ? and contrasena= ?;";
        ResultSet rs = null;
        PreparedStatement ps = null;
        
       
        try {
            

            ps = conec.getConnection().prepareStatement(sql);
            ps.setString(1,user);
             ps.setString(2,pass);
           

        
            
            
            
            
            
                  
            rs = ps.executeQuery();
            while (rs.next()) {
             
            Usuario vo = new Usuario();
            idEmpleado = rs.getString(1);
            System.out.println("aquii"+idEmpleado);
            
            
            System.out.println("Terminóy");
        }


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        
        }finally {
            try {
                ps.close();
                rs.close();
                conec.desconectar();
            } catch (Exception ex) {
            }
        }
         
    
        return idEmpleado;
     }
    public String InicioAdmin(String user , String pass){
         
        Conectar conec = new Conectar();
        String idRol = null;
        
        String sql = "SELECT idRol FROM Usuario WHERE usuario = ? and contrasena= ?;";
        ResultSet rs = null;
        PreparedStatement ps = null;
        
       
        try {
            

            ps = conec.getConnection().prepareStatement(sql);
            ps.setString(1,user);
             ps.setString(2,pass);
           

        
            
            
            
            
            
                  
            rs = ps.executeQuery();
            while (rs.next()) {
             
            Usuario vo = new Usuario();
            idRol = rs.getString(1);
            System.out.println("aquii"+idRol);
            
            
            System.out.println("Terminóy");
        }


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        
        }finally {
            try {
                ps.close();
                rs.close();
                conec.desconectar();
            } catch (Exception ex) {
            }
        }
         
    
        return idRol;
     }
    
     public ArrayList<Actividad> ListarActividadesTotal(){
         ArrayList<Actividad> list = new ArrayList<>();
        Conectar conec = new Conectar();

        
        String sql = "SELECT E.NombreEmpleado, A.idActividad, A.Proyecto, A.JefeInmediato, A.Actividad, A.Porcentaje, A.FechaFin, A.Comentarios, A.JefeInmediato, A.Hora , A.FechaRegistro FROM Actividad A  left JOIN Empleado E ON a.idEmpleado = E.idEmpleado ORDER BY E.nombreEmpleado ASC;";
        ResultSet rs = null;
    PreparedStatement ps = null;
        
       
        try {
            

            ps = conec.getConnection().prepareStatement(sql);
           
           

        
            
            
            
            
            
                  
            rs = ps.executeQuery();
            while (rs.next()) {
             
            Actividad vo = new Actividad();
            vo.setNombreEmpleado(rs.getString(1));
            vo.setIdActividad(rs.getInt(2));
            vo.setProyecto(rs.getString(3));
            vo.setJefe(rs.getString(4));    
            vo.setActividad(rs.getString(5));
            vo.setPorcentajeAvance(rs.getString(6));
            
            vo.setFechaFinEstimada(rs.getDate(7));
            
            vo.setComentarios(rs.getString(8));
            vo.setJefe(rs.getString(9));
            vo.setHora(rs.getString(10));
            vo.setFechaRegistro(rs.getDate(11));
            list.add(vo);
            System.out.println("Terminóy");
        }


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        
        }finally {
            try {
                ps.close();
                rs.close();
                conec.desconectar();
            } catch (Exception ex) {
            }
        }
         
    
        return list;
     }

      public ArrayList<String[]> listarUsuarios() {
    ArrayList<String[]> resultList = new ArrayList<>();
    Conectar conec = new Conectar();

    String sql = "SELECT U.IdUsuario , E.nombreEmpleado, U.Usuario, U.contrasena FROM Usuario U LEFT JOIN Empleado E ON U.idEmpleado = E.idEmpleado ORDER BY E.nombreEmpleado ASC;";
    try (PreparedStatement ps = conec.getConnection().prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        while (rs.next()) {
            String[] rowData = new String[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                rowData[i - 1] = rs.getString(i);
            }
            resultList.add(rowData);
        }

    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }finally {
            try {
                
         
                conec.desconectar();
            } catch (Exception ex) {
            }
        }

    return resultList;
}     
      public String MostrarNombre(String IdEmpleado){
       
        Conectar conec = new Conectar();

        String Nombre = null;
        String sql = "SELECT nombreEmpleado from Empleado WHERE IdEmpleado = ? ;";
        ResultSet rs = null;
    PreparedStatement ps = null;
        
       
        try {
            

            ps = conec.getConnection().prepareStatement(sql);
           
           

        
            ps.setString(1,IdEmpleado);

            
            
            
            
                  
            rs = ps.executeQuery();
            while (rs.next()) {
             
            
             Nombre= rs.getString(1);
            
            System.out.println("Terminóy");
        }


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        
        }finally {
            try {
                ps.close();
                rs.close();
                conec.desconectar();
            } catch (Exception ex) {
            }
        }
         
    
        return Nombre;
     }

      
      
    public ArrayList<Actividad> ListarActividadesPorEmpleadoYFecha(Integer IdEmpleado, Date fecha) {
    ArrayList<Actividad> list = new ArrayList<>();
    Conectar conec = new Conectar();

    String sql = "SELECT E.NombreEmpleado, A.idActividad, A.Proyecto, A.JefeInmediato, A.Actividad, A.Porcentaje, E.idEmpleado ,A.FechaFin, A.Comentarios , A.FechaRegistro FROM Actividad A JOIN Empleado E ON A.idEmpleado = E.idEmpleado WHERE  A.idEmpleado = ?  AND A.FechaRegistro = ?";

    

    ResultSet rs = null;
    PreparedStatement ps = null;

    try {
        ps = conec.getConnection().prepareStatement(sql);

        ps = conec.getConnection().prepareStatement(sql);
            ps.setInt(1,IdEmpleado);
            java.sql.Date sqlDate = null;
            sqlDate = new java.sql.Date(fecha.getTime());
             ps.setDate(2,sqlDate);
           
        rs = ps.executeQuery();

        while (rs.next()) {
            Actividad vo = new Actividad();
            vo.setNombreEmpleado(rs.getString(1));
            vo.setIdActividad(rs.getInt(2));
            vo.setProyecto(rs.getString(3));
            vo.setJefe(rs.getString(4));    
            vo.setActividad(rs.getString(5));
            vo.setPorcentajeAvance(rs.getString(6));
            vo.setIdEmpleado(rs.getInt(7));
            vo.setFechaFinEstimada(rs.getDate(8));
            vo.setComentarios(rs.getString(9));
            vo.setFechaRegistro(rs.getDate(10));
            
            list.add(vo);
System.out.println("Terminó. Fecha buscada: " + fecha);
        }

    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    } finally {
        // Cerrar recursos (ResultSet, PreparedStatement, Connection)
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            conec.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                ps.close();
                rs.close();
                conec.desconectar();
            } catch (Exception ex) {
            }
        }
    }

    return list;
}
  
    
    public ArrayList<Actividad> ListarActividadesPorEmpleado(Integer IdEmpleado) {
    ArrayList<Actividad> list = new ArrayList<>();
    Conectar conec = new Conectar();

    String sql = "SELECT E.NombreEmpleado, A.idActividad, A.Proyecto, A.JefeInmediato, A.Actividad, A.Porcentaje, E.idEmpleado ,A.FechaFin, A.Comentarios , A.fechaRegistro FROM Actividad A JOIN Empleado E ON A.idEmpleado = E.idEmpleado WHERE  E.idEmpleado = ?";

    // Agregar la condición de IdEmpleado si se proporciona
    
    

    
    ResultSet rs = null;
    PreparedStatement ps = null;

    try {
        ps = conec.getConnection().prepareStatement(sql);

        // Configurar los parámetros de IdEmpleado y fecha si se proporcionan
        
            ps.setInt(1, IdEmpleado);
       

        rs = ps.executeQuery();

        while (rs.next()) {
            Actividad vo = new Actividad();
            vo.setNombreEmpleado(rs.getString(1));
            vo.setIdActividad(rs.getInt(2));
            vo.setProyecto(rs.getString(3));
            vo.setJefe(rs.getString(4));    
            vo.setActividad(rs.getString(5));
            vo.setPorcentajeAvance(rs.getString(6));
            vo.setIdEmpleado(rs.getInt(7));
            vo.setFechaFinEstimada(rs.getDate(8));
            vo.setComentarios(rs.getString(9));
            vo.setFechaRegistro(rs.getDate(10));
            list.add(vo);

        }

    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    } finally {
        // Cerrar recursos (ResultSet, PreparedStatement, Connection)
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            conec.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                ps.close();
                rs.close();
                conec.desconectar();
            } catch (Exception ex) {
            }
        }
    }

    return list;
}
     public ArrayList<Actividad> ListarActividadesPorFecha( Date fecha) {
    ArrayList<Actividad> list = new ArrayList<>();
    Conectar conec = new Conectar();

    String sql = "SELECT E.NombreEmpleado, A.idActividad, A.Proyecto, A.JefeInmediato, A.Actividad, A.Porcentaje, E.idEmpleado ,A.FechaFin, A.Comentarios, A.FechaRegistro FROM Actividad A JOIN Empleado E ON A.idEmpleado = E.idEmpleado WHERE A.FechaRegistro = ?";

    System.out.println(fecha);
    // Agregar la condición de fecha si se proporciona
    
    ResultSet rs = null;
    PreparedStatement ps = null;

    try {
        ps = conec.getConnection().prepareStatement(sql);

        // Configurar los parámetros de IdEmpleado y fecha si se proporcionan
        int paramCount = 1;
        
        if (fecha != null) {
            java.sql.Date sqlDate = null;
            if (fecha != null) {
                sqlDate = new java.sql.Date(fecha.getTime());
            }
            ps.setDate(paramCount, sqlDate);
        }

        rs = ps.executeQuery();

        while (rs.next()) {
            Actividad vo = new Actividad();
            vo.setNombreEmpleado(rs.getString(1));
            vo.setIdActividad(rs.getInt(2));
            vo.setProyecto(rs.getString(3));
            vo.setJefe(rs.getString(4));    
            vo.setActividad(rs.getString(5));
            vo.setPorcentajeAvance(rs.getString(6));
            vo.setIdEmpleado(rs.getInt(7));
            vo.setFechaFinEstimada(rs.getDate(8));
            vo.setComentarios(rs.getString(9));
            vo.setFechaRegistro(rs.getDate(10));
            list.add(vo);
System.out.println("Terminó. Fecha buscada: " + fecha);
        }

    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    } finally {
        // Cerrar recursos (ResultSet, PreparedStatement, Connection)
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            conec.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                ps.close();
                rs.close();
                conec.desconectar();
            } catch (Exception ex) {
            }
        }
    }

    return list;
}
      public void registrarEmpleado(Empleado empleado_x){
        Conectar conec = new Conectar();

        
        String sql = "INSERT INTO Empleado (idEmpleado, NombreEmpleado, DNI) VALUES (?,?,?)";
        PreparedStatement ps = null;
        
       
        try {
            

            ps = conec.getConnection().prepareStatement(sql);
            ps.setInt(1,empleado_x.getIdEmpleado());
            ps.setString(2,empleado_x.getNombreEmpleado());
            ps.setString(3,empleado_x.getDNI());
            
            
            
            
            
                  
            ps.executeUpdate();
            System.out.println("Resgistro  Empelado correcto");


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        
        }finally {
            try {
                ps.close();
                conec.desconectar();
            } catch (Exception ex) {
            }
        }
         
    }
    public void registrarUsuario(Usuario usuario_x){
        Conectar conec = new Conectar();

        
        String sql = "INSERT INTO Usuario (idUsuario, Usuario, Contrasena, idEmpleado) VALUES (?,?,?,?)";
        PreparedStatement ps = null;
        
       
        try {
            

            ps = conec.getConnection().prepareStatement(sql);
            ps.setInt(1,usuario_x.getIdUsuario());
            ps.setString(2,usuario_x.getUsuario());
            ps.setString(3,usuario_x.getCosntrasena());
            ps.setString(4,usuario_x.getIdEmpleado());
            
            
            
            
            
            
                  
            ps.executeUpdate();
            System.out.println("Resgistro usuario correcto");


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        
        }finally {
            try {
                ps.close();
                conec.desconectar();
            } catch (Exception ex) {
            }
        }
         
    }
      
      public void actualizarUsuarioYEmpleado(int idUsuario, String nuevoNombre, String nuevoDNI, String nuevoUsuario, String nuevaContrasena) {
    Conectar conec = new Conectar();

    try {
        // Actualizar la tabla Empleado
        String sqlUpdateEmpleado = "UPDATE Empleado SET nombreEmpleado = ?, DNI = ? WHERE idEmpleado = (SELECT idEmpleado FROM Usuario WHERE idUsuario = ?)";
        try (PreparedStatement psUpdateEmpleado = conec.getConnection().prepareStatement(sqlUpdateEmpleado)) {
            psUpdateEmpleado.setString(1, nuevoNombre);
            psUpdateEmpleado.setString(2, nuevoDNI);
            psUpdateEmpleado.setInt(3, idUsuario);

            psUpdateEmpleado.executeUpdate();
        }

        // Actualizar la tabla Usuario
        String sqlUpdateUsuario = "UPDATE Usuario SET Usuario = ?, contrasena = ? WHERE idUsuario = ?";
        try (PreparedStatement psUpdateUsuario = conec.getConnection().prepareStatement(sqlUpdateUsuario)) {
            psUpdateUsuario.setString(1, nuevoUsuario);
            psUpdateUsuario.setString(2, nuevaContrasena);
            psUpdateUsuario.setInt(3, idUsuario);

            psUpdateUsuario.executeUpdate();
        }

    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    } finally {
        try {
            conec.desconectar();
        } catch (Exception ex) {
            // Manejar la excepción de desconexión si es necesario
        }
    }
}

    public void llenarDatosUsuarioYEmpleado(int idUsuario, JTextField txtNombre, JTextField txtDNI, JTextField txtUsuario, JTextField txtContraseña) {
    Conectar conec = new Conectar();

    try {
        // Consultar datos del Usuario y su Empleado asociado
        String sql = "SELECT E.nombreEmpleado, U.Usuario, U.contrasena, E.DNI " +
                     "FROM Usuario U " +
                     "LEFT JOIN Empleado E ON U.idEmpleado = E.idEmpleado " +
                     "WHERE U.idUsuario = ?";
        try (PreparedStatement ps = conec.getConnection().prepareStatement(sql)) {
            ps.setInt(1, idUsuario);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Obtener los datos y asignarlos a los JTextField
                    txtNombre.setText(rs.getString("nombreEmpleado"));
                    txtDNI.setText(rs.getString("DNI"));
                    txtUsuario.setText(rs.getString("Usuario"));
                    txtContraseña.setText(rs.getString("contrasena"));
                } else {
                    // Manejar el caso en el que no se encuentren datos para el idUsuario dado
                    System.out.println("No se encontraron datos para el idUsuario: " + idUsuario);
                }
            }
        }

    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    } finally {
        try {
            conec.desconectar();
        } catch (Exception ex) {
            // Manejar la excepción de desconexión si es necesario
        }
    }
}
 
    public void eliminarUsuarioYEmpleado(int idUsuario) {
    Conectar conec = new Conectar();

    try {
        String sqlDeleteActividades = "DELETE FROM actividad WHERE idEmpleado = (SELECT idEmpleado FROM Usuario WHERE idUsuario = ?)";
        try (PreparedStatement psDeleteUsuario = conec.getConnection().prepareStatement(sqlDeleteActividades)) {
            psDeleteUsuario.setInt(1, idUsuario);
            psDeleteUsuario.executeUpdate();
        }
        // Eliminar de la tabla Empleado
        String sqlDeleteEmpleado = "DELETE FROM Empleado WHERE idEmpleado = (SELECT idEmpleado FROM Usuario WHERE idUsuario = ?)";
        try (PreparedStatement psDeleteEmpleado = conec.getConnection().prepareStatement(sqlDeleteEmpleado)) {
            psDeleteEmpleado.setInt(1, idUsuario);
            psDeleteEmpleado.executeUpdate();
        }

        // Eliminar de la tabla Usuario
        String sqlDeleteUsuario = "DELETE FROM Usuario WHERE idUsuario = ?";
        try (PreparedStatement psDeleteUsuario = conec.getConnection().prepareStatement(sqlDeleteUsuario)) {
            psDeleteUsuario.setInt(1, idUsuario);
            psDeleteUsuario.executeUpdate();
        }
        
        

    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    } finally {
        try {
            conec.desconectar();
        } catch (Exception ex) {
            // Manejar la excepción de desconexión si es necesario
        }
    }
}

    
}
