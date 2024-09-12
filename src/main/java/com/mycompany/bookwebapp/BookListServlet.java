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
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
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
@WebServlet("/booklist")
public class BookListServlet extends HttpServlet {
    private static final String QUERY = "SELECT id as Идентификатор, "
            + "book_title as Название, "
            + "book_edition as Редакция, book_price as Цена FROM book_data ORDER BY id asc";
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
            try {
                /* TODO output your page here. You may use following sample code. */
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(BookRegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            try(Connection connection = DriverManager.getConnection("jdbc:mysql:///book_app", "root", "masterkey");
                    Statement st = connection.createStatement();
                    ResultSet rs = st.executeQuery(QUERY);
                    ) {
                ResultSetMetaData rsmd = rs.getMetaData();
                // формируем страницу с данными
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet BookListServlet</title>");            
                out.println("<link rel='stylesheet' href='.../styles/style.css'");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>СПИСОК ЗАРЕГИСТРИРОВАННЫХ КНИГ</h1><br>");
                out.println("<div class='list' style='width: 100%;" +
                    "margin: 50px auto; border: 1px solid silver;" +
                    "border-radius: 6px;'>");
                out.println("<table cols='4' style='border: 1px solid #bc0505;"
                        + "border-collapse: collapse;width: 100%;'>");// объявляем таблицу
                out.println("<tr><th>" + rsmd.getColumnLabel(1) + "</th><th>" + 
                        rsmd.getColumnLabel(2) + "</th><th>" +
                        rsmd.getColumnLabel(3) + "</th><th>" +
                                rsmd.getColumnLabel(4) + "</th>"
                                        + "<th>Редактировать</th>"
                                        + "<th>Удалить</th></tr>");// заголовки таблицы
                // заполняем данными
                while(rs.next()) {
                    out.println("<tr>"
                            + "<td style='padding-left: 5px;"
                            + "padding-right: 5px;border: 1px solid #bc0505;'>" + 
                            rs.getInt(1) + "</td>");
                    out.println("<td style='padding-left: 5px;"
                            + "padding-right: 5px;border: 1px solid #bc0505;'>" + 
                            rs.getString(2) + "</td>");
                    out.println("<td style='padding-left: 5px;"
                            + "padding-right: 5px;border: 1px solid #bc0505;'>" + 
                            rs.getString(3) + "</td>");
                    out.println("<td style='padding-left: 5px;"
                            + "padding-right: 5px;border: 1px solid #bc0505;'>" + 
                            rs.getFloat(4) + "</td>");
                    out.println("<td style='padding-left: 5px;"
                            + "padding-right: 5px;border: 1px solid #bc0505;'>"
                                    + "<a href='editScreen?id="
                            + rs.getInt(1) + "'>Редактировать</a></td>");
                    out.println("<td style='padding-left: 5px;"
                            + "padding-right: 5px;border: 1px solid #bc0505;'>"
                                    + "<a href='deleteUrl?id="
                            + rs.getInt(1) + "'>Удалить</a></td>");
                    out.println("</tr>");
                }
                out.println("</table><br>");
                out.println("</div>");
                out.println("<a href=\"index.html\">На главную страницу</a>");
                out.println("</body>");
                out.println("</html>");
            } catch(SQLException ex) {
                Logger.getLogger(BookRegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
                out.println("<h2>" + ex.getMessage() + "</h2>");
            }
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
