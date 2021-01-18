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
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "SearchProductServlet", urlPatterns = {"/SearchProductServlet"})
public class SearchProductServlet extends HttpServlet {

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
        String pName = request.getParameter("txtSearchValue");
        String categoryName = request.getParameter("cboCategory");
        String priceMinStr = request.getParameter("txtPriceMin");
        String priceMaxStr = request.getParameter("txtPriceMax");
        int priceMin = 0;
        int priceMax = 1000;
        try {
            ProductDAO dao = new ProductDAO();
            if (!pName.trim().isEmpty() || !categoryName.equals("--Select Category--") || !priceMinStr.trim().isEmpty() || !priceMaxStr.trim().isEmpty()) {
                if (!priceMinStr.trim().isEmpty() && priceMinStr != null) {
                    priceMin = Integer.parseInt(priceMinStr);
                }
                if (!priceMaxStr.trim().isEmpty()) {
                    priceMax = Integer.parseInt(priceMaxStr);
                }
                if (!priceMinStr.trim().isEmpty() && !priceMaxStr.trim().isEmpty()) {
                    if (pName == null && !categoryName.equals("--Select Category--")) {
                        dao.userSearchProductByPrice(priceMin, priceMax, 1);
                        List<ProductDTO> result = dao.getProductList();
                        request.setAttribute("PRODUCTSEARCH", result);
                    }
                }
                if (!pName.trim().isEmpty()) {
                    if (!categoryName.equals("--Select Category--")) {
                        dao.userSearchProductByAll(pName, categoryName, priceMin, priceMax, 1);
                        List<ProductDTO> result = dao.getProductList();
                        request.setAttribute("PRODUCTSEARCH", result);
                    } else {
                        dao.userSearchProductByName(pName, priceMin, priceMax, 1);
                        List<ProductDTO> result = dao.getProductList();
                        request.setAttribute("PRODUCTSEARCH", result);
                    }
                }
                if (!categoryName.equals("--Select Category--")) {
                    if (!pName.trim().isEmpty()) {
                        dao.userSearchProductByCategory(categoryName, priceMin, priceMax, 1);
                        List<ProductDTO> result = dao.getProductList();
                        request.setAttribute("PRODUCTSEARCH", result);
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SearchProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(SearchProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            String url = "searchProduct.jsp";
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
