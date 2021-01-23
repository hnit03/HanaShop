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
import nhinh.daos.ProductDAO;
import nhinh.dtos.ProductDTO;
import org.apache.log4j.Logger;

/**
 *
 * @author PC
 */
@WebServlet(name = "AdminStartUpServlet", urlPatterns = {"/AdminStartUpServlet"})
public class AdminStartUpServlet extends HttpServlet {

    private Logger log = Logger.getLogger(AdminStartUpServlet.class.getName());
    private final String START_UP_CONTROLLER = "StartUpServlet";
    private final String ADMIN_PAGE = "adminPage.jsp";
    private final String ERROR_PAGE = "error.jsp";

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
        String url = ERROR_PAGE;
        try {
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession(false);
            String fullname = (String) session.getAttribute("FULLNAME");
            if (fullname != null) {
                boolean role = (boolean) session.getAttribute("ISADMIN");
                if (role) {
                    int pageNo = 1;
                    Object pageNoObj = request.getAttribute("PAGENO");
                    if (pageNoObj != null) {
                        pageNo = (int) pageNoObj;
                    }
                    ProductDAO dao = new ProductDAO();
                    int pageMax = dao.getNumberOfPageForAdmin();
                    request.setAttribute("PAGE_MAX_ADMIN", pageMax);
                    if (pageNo >= pageMax) {
                        pageNo = pageMax;
                    }
                    dao.getAllProducts(pageNo);
                    List<ProductDTO> result = dao.getProductList();
                    request.setAttribute("ALLPRODUCT", result);

                    url = ADMIN_PAGE;
                }
            } else {
                url = START_UP_CONTROLLER;
            }

        } catch (SQLException ex) {
//            log("AdminStartUp_SQL: " + ex.getMessage());
            log.error(ex.getMessage());
        } catch (NamingException ex) {
//            log("AdminStartUp_Naming: " + ex.getMessage());
            log.error(ex.getMessage());
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
