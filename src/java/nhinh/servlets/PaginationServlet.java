/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhinh.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author PC
 */
@WebServlet(name = "PaginationServlet", urlPatterns = {"/PaginationServlet"})
public class PaginationServlet extends HttpServlet {

    private final String USER_START_UP_CONTROLLER = "UserStartUpServlet";
    private final String ADMIN_START_UP_CONTROLLER = "AdminStartUpServlet";
    private final String SEARCH_PRODUCT_CONTROLLER = "SearchProductServlet";

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
        PrintWriter out = response.getWriter();
        String pageNoStr = request.getParameter("pageNo");
        String paging = request.getParameter("btnAction");
        String pageView = request.getParameter("pageView");
        int pageNo = 1;
        String url = "";
        try {
            /* TODO output your page here. You may use following sample code. */
            if (pageNoStr != null) {
                pageNo = Integer.parseInt(pageNoStr);
            }
            if (paging != null) {
                if (paging.equals("Previous")) {
                    pageNo -= 1;
                } else if (paging.equals("Next")) {
                    pageNo = pageNo + 1;
                }
            }
            if (pageNo < 1) {
                pageNo = 1;
            }
            if (pageView.equals("index.jsp")) {
                url = USER_START_UP_CONTROLLER;
            }else if (pageView.equals("adminPage.jsp")) {
                url = ADMIN_START_UP_CONTROLLER;
            }else if (pageView.equals("searchProduct.jsp")) {
                url = SEARCH_PRODUCT_CONTROLLER;
            }else if (pageView.equals("searchProductByAdmin.jsp")) {
                url = SEARCH_PRODUCT_CONTROLLER;
            }
            request.setAttribute("PAGENO", pageNo);

        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
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
