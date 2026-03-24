/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.CourseDAO;
import dto.AccountDTO;
import dto.CourseDTO;
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
@WebServlet(name = "AdminCourseController", urlPatterns = {"/AdminCourseController"})
public class AdminCourseController extends HttpServlet {

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
        CourseDAO dao = new CourseDAO();

        try {

            if (action != null) {
                switch (action) {

                    // ================= DELETE =================
                    case "delete": {
                        String idStr = request.getParameter("id");
                        if (idStr != null && idStr.matches("\\d+")) {
                            int id = Integer.parseInt(idStr);
                            dao.deleteCourse(id);
                        }
                        response.sendRedirect("main?action=adminCourses");
                        return;
                    }

                    // ================= ADD =================
                    case "add": {
                        String name = request.getParameter("name");
                        String description = request.getParameter("description");
                        String feeStr = request.getParameter("fee");
                        String schedule = request.getParameter("schedule");
                        String startDate = request.getParameter("startDate");

                        if (name != null && feeStr != null && !feeStr.isEmpty()) {
                            double fee = Double.parseDouble(feeStr);
                            dao.addCourse(name, description, fee, schedule, startDate);
                        }

                        response.sendRedirect("main?action=adminCourses");
                        return;
                    }

                    // ================= EDIT =================
                    case "edit": {
                        String idStr = request.getParameter("id");
                        if (idStr != null && idStr.matches("\\d+")) {
                            int id = Integer.parseInt(idStr);
                            CourseDTO c = dao.getCourseById(id);
                            request.setAttribute("EDIT_COURSE", c);
                        }
                        break;
                    }

                    // ================= UPDATE =================
                    case "update": {
                        String idStr = request.getParameter("id");
                        String feeStr = request.getParameter("fee");

                        if (idStr != null && idStr.matches("\\d+") && feeStr != null && !feeStr.isEmpty()) {
                            int id = Integer.parseInt(idStr);

                            String name = request.getParameter("name");
                            String description = request.getParameter("description");
                            double fee = Double.parseDouble(feeStr);
                            String schedule = request.getParameter("schedule");

                            dao.updateCourse(id, name, description, fee, schedule);
                        }

                        response.sendRedirect("main?action=adminCourses");
                        return;
                    }

                    default:
                        break;
                }
            }

            // ================= LOAD LIST =================
            ArrayList<CourseDTO> list = dao.getAllCourses();
            request.setAttribute("COURSE_LIST", list);

        } catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("admin-courses.jsp").forward(request, response);
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
