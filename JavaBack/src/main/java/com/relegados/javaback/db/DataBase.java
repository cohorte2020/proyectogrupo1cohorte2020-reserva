/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.relegados.javaback.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
            String url = "jdbc:sqlite:/home/fddiaz/Projects/proyecto-reservas/DataBase/base.sqlite3";  
            // creando la conecxion 
            conn = DriverManager.getConnection(url);  
            
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }
        return conn;
    }
}
