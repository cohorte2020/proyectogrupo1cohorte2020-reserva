/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.relegados.javaback.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Fedrico Damaso Diaz Ramseyer
 */
public class DataBase {
    public static Connection connect() throws ClassNotFoundException { 
        Class.forName("org.sqlite.JDBC");
        
        Connection conn = null;  
        
        try {  
            // ubicación de la base (path absoluto)
            String url = "jdbc:sqlite:/home/fddiaz/Projects/ISPC/proyectogrupo1cohorte2020-reserva/DataBase/base.sqlite3";  
            // creando la conecxion 
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());  
        }
        return conn;
    }
    
    public static ResultSet executeQuery(String sql) throws ClassNotFoundException {
        ResultSet rs = null;
        try {
            Connection conn = connect();
            Statement stmt  = conn.createStatement();
            rs              = stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rs;

    }
    public static boolean executeUpdate(String sql) throws ClassNotFoundException {
        boolean flag = false;
        try {
            Connection conn = connect();
            Statement pstmt = conn.prepareStatement(sql);
            if (pstmt.executeUpdate(sql) > 0){
                flag = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return flag;
    }
}
