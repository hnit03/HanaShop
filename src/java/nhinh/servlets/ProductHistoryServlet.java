/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhinh.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import nhinh.daos.ProductHistoryDAO;
import nhinh.dtos.ProductHistoryDTO;
import org.apache.log4j.Logger;

/**
 *
 * @author PC
 */
@WebServlet(name = "ProductHistoryServlet", urlPatterns = {"/ProductHistoryServlet"})
public class ProductHistoryServlet extends HttpServlet {

    private final String START_UP_CONTROLLER = "StartUpServlet";
    private final String PRODUCT_HISTORY_PAGE = "productHistory.jsp";
    private Logger log = Logger.getLogger(ProductHistoryServlet.class.getName());
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
        String url = START_UP_CONTROLLER;
        try {
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession(false);
            Object roleObject = session.getAttribute("ISADMIN");
            if (roleObject != null) {
                boolean role = (boolean) roleObject;
                if (role) {
                    Object userIDObj = session.getAttribute("USERID");
                    if (userIDObj != null) {
                        String userID = (String) userIDObj;
                        ProductHistoryDAO dao = new ProductHistoryDAO();
                        dao.getAllProductHistory(userID);
                        List<ProductHistoryDTO> list = dao.getProductHistoryList();
                        request.setAttribute("LIST_PRODUCT_HISTORY", list);
                        url = PRODUCT_HISTORY_PAGE;
                    }
                }
            }
        } catch (SQLException ex) {
            log.error("ProductHistory_SQL:" + ex.getMessage());
        } catch (NamingException ex) {
            log.error("ProductHistory_Naming:" + ex.getMessage());
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
