/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.AccountDAO;
import dto.AccountDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author anvan
 */
@WebServlet(name = "AdminUserController", urlPatterns = {"/AdminUserController"})
public class AdminUserController extends HttpServlet {

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

        HttpSession session = request.getSession();
        AccountDTO user = (AccountDTO) session.getAttribute("LOGIN_USER");

        if (user == null || !"admin".equals(user.getRole())) {
            response.sendRedirect("login.jsp");
            return;
        }

        String action = request.getParameter("subAction");

        AccountDAO dao = new AccountDAO();

        try {

            if (action != null) {
                switch (action) {

                    case "delete": {
                        String idStr = request.getParameter("id");
                        if (idStr != null && idStr.matches("\\d+")) {
                            int id = Integer.parseInt(idStr);
                            dao.deleteUser(id);
                        }
                        response.sendRedirect("main?action=adminUsers");
                        return;
                    }

                    case "add": {
                        String username = request.getParameter("username");
                        String password = request.getParameter("password");
                        String fullname = request.getParameter("fullname");
                        String role = request.getParameter("role");

                        if (username != null && !username.trim().isEmpty()) {
                            dao.addUser(username, password, fullname, role);
                        }

                        response.sendRedirect("main?action=adminUsers");
                        return;
                    }

                    case "edit": {
                        String idStr = request.getParameter("id");
                        if (idStr != null && idStr.matches("\\d+")) {
                            int id = Integer.parseInt(idStr);
                            AccountDTO acc = dao.getUserById(id);
                            request.setAttribute("EDIT_USER", acc);
                        }
                        break;
                    }

                    case "update": {
                        String idStr = request.getParameter("id");
                        if (idStr != null && idStr.matches("\\d+")) {
                            int id = Integer.parseInt(idStr);
                            String fullname = request.getParameter("fullname");
                            String role = request.getParameter("role");

                            dao.updateUser(id, fullname, role);
                        }

                        response.sendRedirect("main?action=adminUsers");
                        return;
                    }

                    default:
                        break;
                }
            }

            ArrayList<AccountDTO> list = dao.getAllUsers();
            request.setAttribute("USER_LIST", list);

        } catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("admin-users.jsp").forward(request, response);
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
