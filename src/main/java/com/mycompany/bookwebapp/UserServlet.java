/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.bookwebapp;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author Sergey
 */
@WebServlet("/userRegistry")
public class UserServlet extends HttpServlet {
    private static final String QUERY = 
            "INSERT INTO user_data(user_name, email, phone_number, age) VALUES(?,?,?,?)";
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UserServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            try {
                /* TODO output your page here. You may use following sample code. */
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(BookRegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            String userName = request.getParameter("user_name");
            String email = request.getParameter("email");
            String phoneNumber = request.getParameter("phone_number");
            short age = Short.parseShort(request.getParameter("age"));
            try(Connection connection = DriverManager.getConnection("jdbc:mysql:///book_app", "root", "masterkey");
                    PreparedStatement ps = connection.prepareStatement(QUERY)) {
                ps.setString(1, userName);
                ps.setString(2, email);
                ps.setString(3, phoneNumber);
                ps.setShort(4, age);
                int row = ps.executeUpdate();
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet UserServlet</title>");            
                out.println("<link rel='stylesheet' href='.../styles/style.css'");
                out.println("</head>");
                out.println("<body>");
                out.println("<div class='list'>");
                if(row > 0) {
                    out.println("<h2>Пользователь зарегистрирован</h2>");
                } else {
                    out.println("<h2>Регистрация пользователя не удалась</h2>");
                }
                out.println("<br><a href='index.html'>На главную страницу</a>");
                out.println("<br><a href='userlist'>Список пользователей</a>");
                out.println("</div>");
                out.println("</body>");
                out.println("</html>");
            } catch(SQLException ex) {
                Logger.getLogger(BookRegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
                out.println("<h2>" + ex.getMessage() + "</h2>");
            }
            
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
