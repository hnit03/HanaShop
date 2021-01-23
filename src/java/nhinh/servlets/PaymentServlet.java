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
import nhinh.daos.ProductDAO;
import nhinh.dtos.ProductDTO;
import org.apache.log4j.Logger;

/**
 *
 * @author PC
 */
@WebServlet(name = "PaymentServlet", urlPatterns = {"/PaymentServlet"})
public class PaymentServlet extends HttpServlet {

    private final String ERROR_PAGE = "error.jsp";
    private final String PAYMENT_BY_CASH_CONTROLLER = "PaymentByCastServlet";
    private final String PAYMENT_WITH_PAYPAL_PAGE = "paymentWithPaypal.jsp";
    private final String CHECK_OUT_PAGE = "checkout.jsp";
    private Logger log = Logger.getLogger(PaymentByCastServlet.class.getName());

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
        try {
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession(false);
            Object roleObj = session.getAttribute("ISADMIN");
            if (roleObj != null) {
                boolean role = (boolean) roleObj;
                if (!role) {
                    CartObject cart = (CartObject) session.getAttribute("CUSTCART");
                    if (cart != null) {
                        Map<ProductDTO, Integer> products = cart.getProducts();
                        if (products != null) {
                            boolean foundErr = false;
                            String productName = "";
                            ProductDAO dao = new ProductDAO();
                            dao.getAllActiveProductsForCheckout();
                            List<ProductDTO> list = dao.getProductList();
                            for (Map.Entry<ProductDTO, Integer> en : cart.getProducts().entrySet()) {
                                for (ProductDTO productDTO : list) {
                                    if (productDTO.getProductID().equals(en.getKey().getProductID())) {
                                        int amountInCart = en.getValue();
                                        if (productDTO.getQuantity() < amountInCart) {
                                            productName = productDTO.getProductName();
                                            foundErr = true;
                                            break;
                                        }
                                    }
                                }
                            }

                            if (foundErr) {
                                request.setAttribute("OUT_OF_STOCK", "The quantity of " + productName + " exceeds the available quantity");
                                url = CHECK_OUT_PAGE;
                            } else {
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
                                        url = PAYMENT_BY_CASH_CONTROLLER;
                                    } else if (paymentMethod.equals("Paypal")) {
                                        session.setAttribute("FULLNAME_PAYMENT_SS", fullname);
                                        session.setAttribute("PHONE_PAYMENT_SS", phoneStr);
                                        session.setAttribute("ADDRESS_PAYMENT_SS", address);
                                        session.setAttribute("TOTAL_PRICE_PAYMENT_SS", totalPriceStr);
                                        url = PAYMENT_WITH_PAYPAL_PAGE;
                                    }
                                }
                            }
                        }
                    }
                }
            }

        } catch (SQLException ex) {
            log.error("Payment_SQL:" + ex.getMessage());
        } catch (NamingException ex) {
            log.error("Payment_Naming:" + ex.getMessage());
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
