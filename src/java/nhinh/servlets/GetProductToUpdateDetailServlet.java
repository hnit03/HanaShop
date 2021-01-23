/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhinh.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
@WebServlet(name = "GetProductToUpdateDetailServlet", urlPatterns = {"/GetProductToUpdateDetailServlet"})
public class GetProductToUpdateDetailServlet extends HttpServlet {

    private final String ADMIN_START_UP_CONTROLLER = "AdminStartUpServlet";
    private final String START_UP_CONTROLLER = "StartUpServlet";
    private final String UPDATE_DETAIL_PAGE = "updateDetail.jsp";
    private Logger log = Logger.getLogger(GetProductToUpdateDetailServlet.class.getName());

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
        String productID = request.getParameter("productID");
        String url = START_UP_CONTROLLER;
        try {
            HttpSession session = request.getSession(false);
            Object roleObj = session.getAttribute("ISADMIN");
            if (roleObj != null) {
                boolean role = (boolean) roleObj;
                if (role) {
                    ProductDAO dao = new ProductDAO();
                    ProductDTO dto = dao.getProductDTOByAdmin(productID);
                    if (dto != null) {
                        request.setAttribute("PRODUCT", dto);
                        url = UPDATE_DETAIL_PAGE;
                    } else {
                        url = ADMIN_START_UP_CONTROLLER;
                    }
                }
            }

        } catch (SQLException ex) {
//            log("GetProductToUpdateDetail_SQL:" + ex.getMessage());
            log.error(ex.getMessage());
        } catch (NamingException ex) {
//            log("GetProductToUpdateDetail_Naming:" + ex.getMessage());
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
