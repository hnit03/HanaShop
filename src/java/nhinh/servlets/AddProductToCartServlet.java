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
import nhinh.cart.CartObject;
import nhinh.daos.ProductDAO;
import nhinh.dtos.ProductDTO;

/**
 *
 * @author PC
 */
@WebServlet(name = "AddProductToCartServlet", urlPatterns = {"/AddProductToCartServlet"})
public class AddProductToCartServlet extends HttpServlet {
    private final String START_UP_CONTROLLER = "StartUpServlet";
    private final String LOGIN_PAGE = "login.jsp";
    private final String VIEW_CART_PAGE = "viewCart.jsp";
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
        String url = LOGIN_PAGE;
        try {
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession(false);
            String fullname = (String) session.getAttribute("FULLNAME");
            if (fullname != null) {
                boolean isAdmin = (boolean) session.getAttribute("ISADMIN");
                if (isAdmin) {
                    url = START_UP_CONTROLLER;
                } else {
                    CartObject cart = (CartObject) session.getAttribute("CUSTCART");
                    if (cart == null) {
                        cart = new CartObject();
                    }
                    String productID = request.getParameter("productID");
                    String amountStr = request.getParameter("txtAmount");
                    String plus = request.getParameter("plus");
                    int amount = 0;
                    if (productID != null) {
                        ProductDAO dao = new ProductDAO();
                        ProductDTO dto = dao.getProductDTO(productID);
                        if (plus != null) {
                            cart.increaseProductToCart(dto.getProductID());
                            url = VIEW_CART_PAGE;
                        } else {
                            if (amountStr != null) {
                                amount = Integer.parseInt(amountStr);
                            }
                            cart.addProductToCart(dto, amount);
                            session.setAttribute("CUSTCART", cart);
                            request.setAttribute("ADD_SUCCESS", true);
                            url = START_UP_CONTROLLER;
                        }

                    }
                    
                }
            }
        } catch (SQLException ex) {
            log("AddProductToCart_SQL:"+ex.getMessage());
        } catch (NamingException ex) {
            log("AddProductToCart_Naming:"+ex.getMessage());
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
