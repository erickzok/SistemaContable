package Entidad;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */


import DAO.serializar;
import Visual.RegistrarActividad;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


public class Conectar {

    public String bd = "actividades";
    public String login = "";
    public String password = "admin";
    public String url = "jdbc:mysql://localhost:3306/actividades" ;
    Connection connection = null;

    public Conectar() {
        try {
            
            serializar serializar = new serializar ();
            
            String bd =serializar.deserializarObjeto("BD.dat",String.class);
            String login = serializar.deserializarObjeto("Login.dat",String.class);
            String password = serializar.deserializarObjeto("Pass.dat",String.class);
            String url = serializar.deserializarObjeto("URL.dat",String.class);
            
            
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, login, password);
            if (connection != null) {
                System.out.println("Conexión a base de datos " + bd + " OK\n");
            }
        } catch (SQLException ex) {
            System.out.println("a"+ex.getMessage());
        } catch (Exception ex) {
            System.out.println("b"+ex.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void desconectar() {
        try {
            connection.close();
        } catch (Exception ex) {
        }
    }

public void setVariableGlobal(String bd , String login , String pass , String url) {
        this.bd = bd;
        this.login=login;
        this.password=pass;
        this.url=url;
    }
}
