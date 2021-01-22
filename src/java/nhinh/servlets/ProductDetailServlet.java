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
import nhinh.daos.ProductDAO;
import nhinh.dtos.ProductDTO;

/**
 *
 * @author PC
 */
@WebServlet(name = "ProductDetailServlet", urlPatterns = {"/ProductDetailServlet"})
public class ProductDetailServlet extends HttpServlet {
    private final String START_UP_CONTROLLER = "StartUpServlet";
    private final String PRODUCT_RECOMMENDATION_CONTROLLER = "ProductRecommendationServlet";
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
        String productIDStr = request.getParameter("productID");
        String url = START_UP_CONTROLLER;
        try {
            /* TODO output your page here. You may use following sample code. */
            if (productIDStr!=null) {
                ProductDAO dao = new ProductDAO();
                int productID = Integer.parseInt(productIDStr);
                ProductDTO dto = dao.getProductDTO(productID);
                if (dto.getQuantity() == 0) {
                    request.setAttribute("QUANTITY", 0);
                }
                request.setAttribute("PRODUCTDETAIL", dto);
                request.setAttribute("PRODUCTID", productID);
                url = PRODUCT_RECOMMENDATION_CONTROLLER;
            }
        } catch (SQLException ex) {
            log("ProductDetail_SQL:"+ex.getMessage());
        } catch (NamingException ex) {
            log("ProductDetail_Naming:"+ex.getMessage());
        }finally{
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
