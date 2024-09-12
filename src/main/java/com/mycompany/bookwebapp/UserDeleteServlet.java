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
@WebServlet("/deleteUserUrl")
public class UserDeleteServlet extends HttpServlet {
    private static final String QUERY = "DELETE FROM user_data WHERE id=?;";
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
            out.println("<title>Servlet UserDeleteServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserDeleteServlet at " + request.getContextPath() + "</h1>");
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
            System.out.println("DOPOST");
            int id = Integer.parseInt(request.getParameter("id"));
            try(Connection connection = DriverManager.getConnection("jdbc:mysql:///book_app", "root", "masterkey");
                    PreparedStatement ps = connection.prepareStatement(QUERY)) {
                ps.setInt(1, id);
                int row = ps.executeUpdate();
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>BookEditServlet</title>");            
                out.println("<link rel='stylesheet' href='.../styles/style.css'");
                out.println("</head>");
                out.println("<body>");
                out.println("<div class='list'>");
                
                if(row == 1) {
                    out.println("<h2>Удление книги прошло успешно</h2>");
                } else {
                    out.println("<h2>Удалениение не удалось</h2>");
                }
                out.println("<br><a href='index.html'>На главную страницу</a>");
                out.println("<br><a href='booklist'>Список пользователей</a>");
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
