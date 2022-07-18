/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.relegados.javaback.Servlet;

import com.relegados.javaback.api.ApiRequest;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fedrico Damaso Diaz Ramseyer
 */
public class ApiReservas extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ApiRequest api = new ApiRequest();
            /* 
            Le paso la tabla por parametro GET
            http://localhost:8080/JavaBack/api?table=users
            */ 
            String acction = request.getParameter("acction");
            String table = request.getParameter("table");
            String column = request.getParameter("column");
            String data = request.getParameter("data");
            String data_from = request.getParameter("data_from");
            String data_to = request.getParameter("data_to");
            String users_id = request.getParameter("users_id");
            String products_id = request.getParameter("products_id");
            
            String responseJson = null;
            
            switch (acction) {
                case "select":
                    responseJson = api.selectFrom(table);
                    break;
                case "where":
                    responseJson = api.selectFromWhere(table, column, data);
                    break;
                case "booking":
                    responseJson = api.insertBooking(data_from, data_to, users_id, products_id);
                    break;
                default:
                    throw new AssertionError();
            }
            
            /*
            if (column != null & data != null){
                responseJson = api.selectFromWhere(table, column, data);
            } else {
                responseJson = api.selectFrom(table);
            }
            */
            
            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(responseJson);
            out.flush();
            //processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ApiReservas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}