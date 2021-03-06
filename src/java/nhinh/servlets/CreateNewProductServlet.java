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
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import nhinh.daos.CategoryDAO;
import nhinh.daos.ProductDAO;
import nhinh.dtos.CategoryDTO;
import nhinh.utils.Utils;
import org.apache.log4j.Logger;

/**
 *
 * @author PC
 */
@WebServlet(name = "CreateNewProductServlet", urlPatterns = {"/CreateNewProductServlet"})
public class CreateNewProductServlet extends HttpServlet {

    private final String CREATE_PRODUCT_PAGE = "createNewProduct.jsp";
    private final String ADMIN_START_UP_CONTROLLER = "AdminStartUpServlet";
    private final String START_UP_CONTROLLER = "StartUpServlet";
    private Logger log = Logger.getLogger(CreateNewProductServlet.class.getName());

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
            HttpSession session = request.getSession(false);
            Object roleObj = session.getAttribute("ISADMIN");
            if (roleObj != null) {
                boolean role = (boolean) roleObj;
                if (role) {
                    ProductDAO pdao = new ProductDAO();
                    if (pdao.checkDupName(productName)) {
                        request.setAttribute("ERROR_PRODUCTNAME", "This name is duplicated.");
                        url = CREATE_PRODUCT_PAGE;
                    } else {
                        int quantity = Integer.parseInt(quantityStr);
                        float price = Float.parseFloat(priceStr);
                        Part filePart = request.getPart("file");
                        String img = filePart.getSubmittedFileName();
                        int lastIndex = img.lastIndexOf(".");
                        String ext = "." + img.substring(lastIndex + 1);
                        String imageName = "";
                        if (productName.contains(" ")) {
                            imageName = utils.formatImageName(productName);
                            img = imageName + ext;
                        } else {
                            img = productName + ext;
                        }
                        String createDate = utils.formatDateToString(newDate);
                        CategoryDAO cdao = new CategoryDAO();
                        CategoryDTO cdto = cdao.getCategoryDTO(category);
                        boolean isCreate = pdao.createNewProduct(productName, img, description, price, createDate, cdto, true, quantity);
                        if (isCreate) {
                            String realPath = request.getServletContext().getRealPath("/");
                            realPath = realPath + "images/";
                            utils.storeFile(realPath, img, filePart);
                            url = ADMIN_START_UP_CONTROLLER;
                            request.setAttribute("CREATE_SUCCESSFULLY", true);
                        }
                    }
                } else {
                    url = START_UP_CONTROLLER;
                }
            }

        } catch (SQLException ex) {
//            log("CreateNewProduct_SQL:" + ex.getMessage());
            log.error("CreateNewProduct_SQL:" +ex.getMessage());
        } catch (NamingException ex) {
//            log("CreateNewProduct_Naming:" + ex.getMessage());
            log.error("CreateNewProduct_Naming:" +ex.getMessage());
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
