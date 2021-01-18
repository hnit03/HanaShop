/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhinh.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import nhinh.daos.CategoryDAO;
import nhinh.daos.ProductDAO;
import nhinh.dtos.CategoryDTO;
import nhinh.dtos.ProductDTO;
import nhinh.utils.Utils;

/**
 *
 * @author PC
 */
@WebServlet(name = "UpdateProductServlet", urlPatterns = {"/UpdateProductServlet"})
public class UpdateProductServlet extends HttpServlet {

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
        String categoryName = request.getParameter("cboCategory");
        String statusStr = request.getParameter("cboStatus");
        String updateDetail = request.getParameter("updateDetail");
        boolean status = false;
        try {
            /* TODO output your page here. You may use following sample code. */
            if (statusStr.equals("Active")) {
                status = true;
            }
            int productID = 0;
            if (productIDStr != null) {
                productID = Integer.parseInt(productIDStr);
            }
            CategoryDAO cdao = new CategoryDAO();
            CategoryDTO cdto = cdao.getCategoryDTO(categoryName);
            ProductDAO pdao = new ProductDAO();
            Utils utils = new Utils();
            if (updateDetail.equals("false")) {
                boolean updateSuccess = pdao.updateQuickly(productID, cdto.getCategoryID(), status);
                if (updateSuccess) {
                    request.setAttribute("UPDATE_SUCCESS", true);
                }
            } else if (updateDetail.equals("true")) {
                ProductDTO oldDTO = pdao.getProductDTOByAdmin(productID);
                String productName = request.getParameter("txtProductName");
                Part filePart = request.getPart("file");
                String img = filePart.getSubmittedFileName();
                if (img.equals("")) {
                    img = oldDTO.getImage();
                }
                int lastIndex = img.lastIndexOf(".");
                String ext = "." + img.substring(lastIndex + 1);
                img = productName + ext;
                String description = request.getParameter("txtDescription");
                String priceStr = request.getParameter("txtPrice");
                String quantityStr = request.getParameter("txtQuantity");
                String createDate = request.getParameter("createDate");
                float price = 0;
                if (priceStr != null) {
                    price = Float.parseFloat(priceStr);
                }
                int quantity = 0;
                if (quantityStr != null) {
                    quantity = Integer.parseInt(quantityStr);
                }
                ProductDTO dto = new ProductDTO(productID, productName, img, description, price, createDate, cdto, status, quantity);
                boolean updateSuccess = pdao.updateProduct(dto);
                if (updateSuccess) {
                    String realPath = request.getServletContext().getRealPath("/");
                    realPath = realPath + "images/";
                    utils.storeFile(realPath, img, filePart);
                    request.setAttribute("UPDATE_SUCCESS", true);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UpdateProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(UpdateProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher("AdminStartUpServlet");
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
