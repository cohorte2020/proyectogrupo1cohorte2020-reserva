/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.relegados.javaback.api;

import com.relegados.javaback.db.DataBase;
import static com.relegados.javaback.db.DataBase.connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;  
import java.sql.ResultSetMetaData;
import java.sql.SQLException;  
import java.sql.Statement;

/**
 *
 * @author Fedrico Damaso Diaz Ramseyer
 */
public class ApiRequest {
    
    public String makeJson(String table, String sql) throws ClassNotFoundException{
        /*
        Funsión que genera el JSON, tomando como parámetros la TABLA (table) y 
        la CONSULRA (sql) a dicha tabla.
        */
        String json = "{\"" + table + "\":[";

        try {
            ResultSet rs    = DataBase.executeQuery(sql);

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
    
    public boolean queryBoolean(String sql) throws ClassNotFoundException {
        /*
        Función que devuelve VERDADERO (true) si la consulta tiene UNA o MÁS
        resultados.
        */
        boolean flag = false;

        try {
            ResultSet rs    = DataBase.executeQuery(sql);

            if (rs.getInt(sql) > 0) {
                flag = true;
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return flag;
    }
    
    public String selectFrom(String table) throws ClassNotFoundException{  
        String sql = "SELECT * FROM " + table;
        return makeJson(table, sql);
    }
    
    public String selectFromWhere(String table, String column, String data) throws ClassNotFoundException{  
        String sql = "SELECT * FROM " + table + " WHERE " + column + " = " + data;
        return makeJson(table, sql);
    }
    
    public boolean takenDate(String data_from, String data_to, String producs_id) throws ClassNotFoundException{
        String sql = "SELECT 1 FROM reservations WHERE (data_from BETWEEN " 
                + data_from + " AND " + data_to + ")" 
                + " OR " 
                + "(data_to BETWEEN " + data_from + " AND " + data_to + ")"
                + " AND "
                + "products_id =" + producs_id;
        return queryBoolean(sql);
    }
    
    public String insertBooking(String data_from, String data_to, String user_id, String product_id ) throws ClassNotFoundException{
        // TODO + FIX
        // Insertar una reserva
        /* 
        String sql = "INSERT INTO "
                + "reservations(data_from, data_to, uesrt_id, product_id) "
                + "VALUES (" 
                    + data_from + ", " 
                    + data_to + ", " 
                    + user_id + ", " 
                    + product_id + ")";
        // String status = "{ status: \"taken\" }";
        String status = "";
        if (!takenDate(data_from, data_to, product_id)){
            try {
            Connection conn = connect();
            Statement stmt  = conn.createStatement();
            status          = String.valueOf(stmt.executeUpdate(sql));
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
            try {
                boolean done    = DataBase.executeUpdate(sql);
                if (done) {
                    status = "{ status: \"ok\" }";
                }
            } catch (ClassNotFoundException e) {
                
            }
        }
        
        return status;
        */
        
        String sql = "INSERT INTO "
                + "RESERVATIONS (data_from, data_to, USERS_id, PRODUCTS_id) "
                + "VALUES ( ?, ?, ?, ?)";
        int result = 0;
        try (
                Connection conn = DataBase.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, data_from);
            pstmt.setString(2, data_to);
            pstmt.setString(3, user_id);
            pstmt.setString(4, product_id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return String.valueOf(result);
    }
    
    public boolean deleteBooking() {
        // TODO
        return true;
    }
}
