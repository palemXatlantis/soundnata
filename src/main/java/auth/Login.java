/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package auth;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Leptop
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

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
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE email='" + email + "'")) {

            if (rs.next()) {
                System.out.println(rs.getString("email"));
                // Verify the provided password against the hashed password
                if (BCrypt.checkpw(password, rs.getString("password"))) {
                    // Login successful, redirect or set session
                    request.getSession().setAttribute("authenticatedUser", email);
                    response.sendRedirect("index.jsp");
                } else {
                    // Login failed, handle error
                    request.setAttribute("error", "Invalid credentials");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            } else {
                // User not found, handle error
                request.setAttribute("error", "User not found");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            System.out.println("Database error during login: " + e.getMessage());
        }
    }

}
