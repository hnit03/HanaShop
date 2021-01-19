/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhinh.servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author PC
 */
@MultipartConfig
public class DispatchServlet extends HttpServlet {

    private final String START_UP_CONTROLLER = "StartUpServlet";
    private final String LOGIN_PAGE = "login.jsp";
    private final String LOGIN_CONTROLLER = "LoginServlet";
    private final String CREATE_NEW_PRODUCT_CONTROLLER = "CreateNewProductServlet";
    private final String CREATE_PRODUCT_PAGE = "createNewProduct.jsp";
    private final String UPDATE_PRODUCT_CONTROLLER = "UpdateProductServlet";
    private final String GET_PRODUCT_CONTROLLER = "GetProductToUpdateDetailServlet";
    private final String SEARCH_PRODUCT_PAGE = "searchProduct.jsp";
    private final String SEARCH_PRODUCT_CONTROLLER = "SearchProductServlet";
    private final String ADD_PRODUCT_TO_CART_CONTROLLER = "AddProductToCartServlet";
    private final String PRODUCT_DETAIL_CONTROLLER = "ProductDetailServlet";
    private final String VIEW_CART_PAGE = "viewCart.jsp";
    private final String REMOVE_PRODUCT_CONTROLLER = "RemoveProductFromCartServlet";
    private final String CHECKOUT_CONTROLLER = "CheckoutServlet";
    private final String LOGOUT_CONTROLLER = "LogoutServlet";
    private final String PAYMENT_CONTROLLER = "PaymentServlet";
    private final String PAGINATION_CONTROLLER = "PaginationServlet";

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
        String url = START_UP_CONTROLLER;
        String button = request.getParameter("btnAction");
        try {
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession(false);
            if (session != null) {
                String fullname = (String) session.getAttribute("FULLNAME");
                if (fullname != null) {
                    boolean role = (boolean) session.getAttribute("ISADMIN");
                    if (role) {
                        if ("Create New Product".equals(button)) {
                            url = CREATE_NEW_PRODUCT_CONTROLLER;
                        } else if ("Create".equals(button)) {
                            url = CREATE_PRODUCT_PAGE;
                        } else if ("Update".equals(button)) {
                            url = UPDATE_PRODUCT_CONTROLLER;
                        } else if ("Update Detail".equals(button)) {
                            url = GET_PRODUCT_CONTROLLER;
                        } else if ("Save".equals(button)) {
                            url = UPDATE_PRODUCT_CONTROLLER;
                        } else if ("Search By Admin Button".equals(button)) {
                            url = SEARCH_PRODUCT_PAGE;
                        } else if ("Search By Admin".equals(button)) {
                            url = SEARCH_PRODUCT_CONTROLLER;
                        }
                    }
                    if (!role) {
                        if ("Search Button".equals(button)) {
                            url = SEARCH_PRODUCT_PAGE;
                        } else if ("Search".equals(button)) {
                            url = SEARCH_PRODUCT_CONTROLLER;
                        } else if ("Add To Cart".equals(button) || "Plus".equals(button)) {
                            url = ADD_PRODUCT_TO_CART_CONTROLLER;
                        } else if ("More".equals(button)) {
                            url = PRODUCT_DETAIL_CONTROLLER;
                        } else if ("View Cart".equals(button)) {
                            url = VIEW_CART_PAGE;
                        } else if ("Remove".equals(button) || "Minus".equals(button)) {
                            url = REMOVE_PRODUCT_CONTROLLER;
                        } else if ("Checkout".equals(button)) {
                            url = CHECKOUT_CONTROLLER;
                        } else if ("Pay".equals(button)) {
                            url = PAYMENT_CONTROLLER;
                        } else if ("Next".equals(button) || "Previous".equals(button)) {
                            url = PAGINATION_CONTROLLER;
                        }
                    }
                }
                if ("".equals(button)) {
                    url = START_UP_CONTROLLER;
                } else if ("Search Button".equals(button)) {
                    url = SEARCH_PRODUCT_PAGE;
                } else if ("Search".equals(button)) {
                    url = SEARCH_PRODUCT_CONTROLLER;
                } else if ("More".equals(button)) {
                    url = PRODUCT_DETAIL_CONTROLLER;
                } else if ("Login Button".equals(button)) {
                    url = LOGIN_PAGE;
                } else if ("Add To Cart".equals(button)) {
                    url = ADD_PRODUCT_TO_CART_CONTROLLER;
                } else if ("Login".equals(button)) {
                    url = LOGIN_CONTROLLER;
                } else if ("Logout".equals(button)) {
                    url = LOGOUT_CONTROLLER;
                } else if ("Next".equals(button) || "Previous".equals(button)) {
                    url = PAGINATION_CONTROLLER;
                }

            }
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
