/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhinh.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import nhinh.cart.CartObject;
import nhinh.daos.BillDAO;
import nhinh.daos.BillDetailsDAO;
import nhinh.daos.ProductDAO;
import nhinh.daos.UserDetailsDAO;
import nhinh.dtos.BillDTO;
import nhinh.dtos.BillDetailsDTO;
import nhinh.dtos.ProductDTO;
import nhinh.dtos.UserDetailsDTO;
import nhinh.utils.Utils;

/**
 *
 * @author PC
 */
@WebServlet(name = "PaymentServlet", urlPatterns = {"/PaymentServlet"})
public class PaymentServlet extends HttpServlet {

    private final String ERROR_PAGE = "error.jsp";
    private final String PAYMENT_SUCCESS_PAGE = "paymentSuccess.jsp";

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
        int numOfProduct = 1;
        Date dateTime = new Date();
        try {
            /* TODO output your page here. You may use following sample code. */

            HttpSession session = request.getSession(false);
            Object roleObject = session.getAttribute("ISADMIN");
            if (roleObject != null) {
                boolean role = (boolean) roleObject;
                if (!role) {
                    String paymentMethod = request.getParameter("paymentMethod");
                    String fullname = request.getParameter("txtFullnameInfo");
                    String phoneStr = request.getParameter("txtPhone");
                    String address = request.getParameter("txtAddress");
                    String totalPriceStr = request.getParameter("txtTotalPrice");
                    if (paymentMethod != null) {
                        if (paymentMethod.equals("Cash")) {
                            request.setAttribute("FULLNAME_PAYMENT_REQ", fullname);
                            request.setAttribute("PHONE_PAYMENT_REQ", phoneStr);
                            request.setAttribute("ADDRESS_PAYMENT_REQ", address);
                            request.setAttribute("TOTAL_PRICE_PAYMENT_REQ", totalPriceStr);
                            url = "PaymentByCastServlet";
                        } else if (paymentMethod.equals("Paypal")) {
                            session.setAttribute("FULLNAME_PAYMENT_SS", fullname);
                            session.setAttribute("PHONE_PAYMENT_SS", phoneStr);
                            session.setAttribute("ADDRESS_PAYMENT_SS", address);
                            session.setAttribute("TOTAL_PRICE_PAYMENT_SS", totalPriceStr);
                            url = "paypalWithPaypal.jsp";
                        }
                    }
                }
            }
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
