/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */


package Controladores;


import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/RegistrarLibro")
public class RegistrarLibro extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/base_datos_final";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASS = "";

    private static final String INSERT_USER_SQL = "INSERT INTO libro (isbn,titulo_libro,anio_publicacion,autor,clasificacion,cantidad_paginas,tipo_pasta_id_tipo_pasta) VALUES (?,?,?,?,?,?,?)";

    public RegistrarLibro() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String isbn = request.getParameter("isbn");
        String titulo = request.getParameter("titulo");
        String anioPublicacion = request.getParameter("anio");
        String autor = request.getParameter("autor");
        String clasificacion= request.getParameter("clasificacion");
        String paginas = request.getParameter("paginas");
        String idTipoPasta = request.getParameter("pasta");     

        try {
           
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL);
            preparedStatement.setString(1, isbn);
            preparedStatement.setString(2, titulo);
            preparedStatement.setString(3, anioPublicacion);
            preparedStatement.setString(4, autor);            
            preparedStatement.setString(5, clasificacion);           
            preparedStatement.setString(6, paginas);            
            preparedStatement.setString(7, idTipoPasta);
            
            int result = preparedStatement.executeUpdate();
            
            if (result > 0) {
                response.sendRedirect("agregarLibro.jsp?success=registered");
            } else {
                response.sendRedirect("agregarLibro.jsp?error=registrationFailed");
            }

            preparedStatement.close();
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("agregarLibro.jsp?error=exception");
        }
    }

}