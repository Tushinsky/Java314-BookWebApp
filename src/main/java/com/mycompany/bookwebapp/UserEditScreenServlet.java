/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bookwebapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sergii.Tushinskyi
 */
@WebServlet("/userScreen")
public class UserEditScreenServlet extends HttpServlet {
    private static final String QUERY = "SELECT user_name, email, "
            + "phone_number, age FROM user_data WHERE id=?";
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
            out.println("<title>Servlet UserEditScreenServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserEditScreenServlet at " + request.getContextPath() + "</h1>");
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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            try {
                /* TODO output your page here. You may use following sample code. */
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(BookRegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            int id = Integer.parseInt(request.getParameter("id"));
            try(Connection connection = DriverManager.getConnection("jdbc:mysql:///book_app", "root", "masterkey");
                    PreparedStatement ps = connection.prepareStatement(QUERY)) {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                rs.next();
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>UserEditScreenServlet</title>");            
                out.println("<link rel='stylesheet' href='.../styles/style.css'");
                out.println("</head>");
                out.println("<body>");
                out.println("<div class='list'>");
                out.println("<form action='userEditUrl?id=" + id + "' method='post'>");
                
                out.println("<table class='content'>");
                
                out.println("<tr>");
                    out.println("<td>ИМЯ</td>");
                    out.println("<td><input type='text' name='user_name' value='" + 
                    rs.getString(1) + "'></td>");
                out.println("</tr>");
                
                out.println("<tr>");
                    out.println("<td>EMAIL</td>");
                    out.println("<td><input type='text' name='email' value='" + 
                    rs.getString(2) + "'></td>");
                out.println("</tr>");
                
                out.println("<tr>");
                    out.println("<td>КОНТАКТНЫЙ ТЕЛЕФОН</td>");
                    out.println("<td><input type='text' name='phone_number' value='" + 
                    rs.getString(3) + "'></td>");
                out.println("</tr>");
                
                out.println("<tr>");
                    out.println("<td>ВОЗРАСТ</td>");
                    out.println("<td><input type='text' name='age' value='" + 
                    rs.getShort(4) + "'></td>");
                out.println("</tr>");
                
                out.println("<tr>");
                    out.println("<td><input type='submit' value='изменить' class='submit'></td>");
                out.println("</tr>");
                out.println("<tr>");
                    out.println("<td><input type='reset' value='отменить' class='reset'></td>");
                out.println("</tr>");
                
                out.println("</table>");
                
                out.println("</form>");
                out.println("<br><a href='index.html'>Главная</a>");
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
        processRequest(request, response);
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
