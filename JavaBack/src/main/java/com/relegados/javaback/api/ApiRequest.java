/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.relegados.javaback.api;

import com.relegados.javaback.db.DataBase;
import java.sql.Connection;  
import java.sql.ResultSet;  
import java.sql.ResultSetMetaData;
import java.sql.SQLException;  
import java.sql.Statement;  

/**
 *
 * @author Fedrico Damaso Diaz Ramseyer
 */
public class ApiRequest {
    
    public String selectFrom(String table) throws ClassNotFoundException{  
        String sql = "SELECT * FROM " + table;
        String json = "{\"" + table + "\":[";

        Class.forName("org.sqlite.JDBC");

        try {
            Connection conn = DataBase.connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnsNumber = rsmd.getColumnCount();

                json += "\n{";

                for(int i = 1; i <= columnsNumber; i++) {
                    json += "\n\t\"" + rsmd.getColumnName(i) + "\" : \"" + rs.getString(i) + "\"";
                    if (i != columnsNumber)
                        json += ",";
                }
                json += "\n},";

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        // Quitando la última coma.
        json = json.substring(0, json.length()-1);
        json += "\n]}";
        return json;
    }
    
    public String selectFromWhere(String table, String column, String data) throws ClassNotFoundException{  
        String sql = "SELECT * FROM " + table + " WHERE " + column +"=" + data;
        String json = "{\"" + table + "\":[";

        Class.forName("org.sqlite.JDBC");

        try {
            Connection conn = DataBase.connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnsNumber = rsmd.getColumnCount();

                json += "\n{";

                for(int i = 1; i <= columnsNumber; i++) {
                    json += "\n\t\"" + rsmd.getColumnName(i) + "\" : \"" + rs.getString(i) + "\"";
                    if (i != columnsNumber)
                        json += ",";
                }
                json += "\n},";

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        // Quitando la última coma.
        json = json.substring(0, json.length()-1);
        json += "\n]}";
        return json;
    }
    
    public boolean insertReser(String title, String desc, String data_from, String data_to, String category_id ){
        // TODO
        // Insertar una reserva
        return true;
    }
    
}
