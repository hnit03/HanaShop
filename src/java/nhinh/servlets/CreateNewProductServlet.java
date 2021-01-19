/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhinh.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
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
@WebServlet(name = "CreateNewProductServlet", urlPatterns = {"/CreateNewProductServlet"})
public class CreateNewProductServlet extends HttpServlet {

    private final String CREATE_PRODUCT_PAGE = "createNewProduct.jsp";
    private final String ADMIN_START_UP_CONTROLLER = "AdminStartUpServlet";

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
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        Date newDate = new Date();
        String productName = request.getParameter("txtProductName");
        String description = request.getParameter("txtDescription");
        String priceStr = request.getParameter("txtPrice");
        String category = request.getParameter("cboCategory");
        String quantityStr = request.getParameter("txtQuantity");
        String url = CREATE_PRODUCT_PAGE;
        Utils utils = new Utils();
        try {
            ProductDAO pdao = new ProductDAO();
            int productID = pdao.getLastProduct();
            if (productID == 0) {
                productID = 10000;
            } else {
                productID++;
            }
            int quantity = Integer.parseInt(quantityStr);
            float price = Float.parseFloat(priceStr);
            Part filePart = request.getPart("file");
            String img = filePart.getSubmittedFileName();
            int lastIndex = img.lastIndexOf(".");
            String ext = "." + img.substring(lastIndex + 1);
            img = productName + ext;
            String createDate = utils.formatDateToString(newDate);
            CategoryDAO cdao = new CategoryDAO();
            CategoryDTO cdto = cdao.getCategoryDTO(category);
            ProductDTO dto = new ProductDTO(productID, productName, img, description, price, createDate, cdto, true, quantity);
            boolean isCreate = pdao.createNewProduct(dto);
            if (isCreate) {
                String realPath = request.getServletContext().getRealPath("/");
                realPath = realPath + "images/";
                utils.storeFile(realPath, img, filePart);
                url = ADMIN_START_UP_CONTROLLER;
                request.setAttribute("CREATE_SUCCESSFULLY", true);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CreateNewProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(CreateNewProductServlet.class.getName()).log(Level.SEVERE, null, ex);
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