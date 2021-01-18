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
    private final String LOGIN_CONTROLLER = "LoginServlet";

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
                            url = "CreateNewProductServlet";
                        } else if ("Create".equals(button)) {
                            url = "createNewProduct.jsp";
                        } else if ("Update".equals(button)) {
                            url = "UpdateProductServlet";
                        } else if ("Update Detail".equals(button)) {
                            url = "GetProductToUpdateDetailServlet";
                        } else if ("Save".equals(button)) {
                            url = "UpdateProductServlet";
                        }
                    }
                    if (!role) {
                        if ("Search Button".equals(button)) {
                            url = "searchProduct.jsp";
                        } else if ("Search".equals(button)) {
                            url = "SearchProductServlet";
                        } else if ("Order".equals(button) || "Plus".equals(button)) {
                            url = "AddProductToCartServlet";
                        } else if ("More".equals(button)) {
                            url = "ProductDetailServlet";
                        } else if ("View Cart".equals(button)) {
                            url = "viewCart.jsp";
                        } else if ("Remove".equals(button) || "Minus".equals(button)) {
                            url = "RemoveProductFromCartServlet";
                        } else if ("Checkout".equals(button)) {
                            url = "CheckoutServlet";
                        } else if ("Pay".equals(button)) {
                            url = "PaymentServlet";
                        }
                    }
                }
                if ("Search Button".equals(button)) {
                    url = "searchProduct.jsp";
                } else if ("Search".equals(button)) {
                    url = "SearchProductServlet";
                } else if ("More".equals(button)) {
                    url = "ProductDetailServlet";
                } else if ("Login Button".equals(button)) {
                    url = "login.jsp";
                } else if ("Order".equals(button)) {
                    url = "AddProductToCartServlet";
                }

                if (button == null) {
                } else if ("Login".equals(button)) {
                    url = LOGIN_CONTROLLER;
                } else if ("Logout".equals(button)) {
                    url = "LogoutServlet";
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
