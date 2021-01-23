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
import nhinh.dtos.ProductDTO;
import nhinh.utils.Utils;
import org.apache.log4j.Logger;

/**
 *
 * @author PC
 */
@WebServlet(name = "PaymentByCastServlet", urlPatterns = {"/PaymentByCastServlet"})
public class PaymentByCastServlet extends HttpServlet {

    private final String ERROR_PAGE = "error.jsp";
    private final String PAYMENT_SUCCESSFUL_PAGE = "paymentSuccess.jsp";
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
            String fullname = (String) request.getAttribute("FULLNAME_PAYMENT_REQ");
            String phoneStr = (String) request.getAttribute("PHONE_PAYMENT_REQ");
            String address = (String) request.getAttribute("ADDRESS_PAYMENT_REQ");
            String totalPriceStr = (String) request.getAttribute("TOTAL_PRICE_PAYMENT_REQ");
            //check null
            if (fullname != null && phoneStr != null && address != null && totalPriceStr != null) {
                int phone = Integer.parseInt(phoneStr);
                HttpSession session = request.getSession(false);
                // get cart
                CartObject cart = (CartObject) session.getAttribute("CUSTCART");
                if (cart != null) {
                    Map<ProductDTO, Integer> products = cart.getProducts();
                    int numOfProduct = 1;
                    Date dateTime = new Date();
                    //check products not equal null
                    if (products != null) {
                        float totalPrice = Float.parseFloat(totalPriceStr);
                        Object numObj = session.getAttribute("NUM_OF_PRODUCT");
                        if (numObj != null) {
                            numOfProduct = (int) numObj;
                        }
                        // create bill
                        BillDAO bdao = new BillDAO();
                        String userID = (String) session.getAttribute("USERID");

                        Utils utils = new Utils();
                        String dateTimeStr = utils.formatDateTimeToString(dateTime);

                        UserDetailsDAO uddao = new UserDetailsDAO();
                        //insert user detail

                        uddao.insertUserDetails(userID, fullname, phone, address);
                        ProductDAO pdao = new ProductDAO();
                        BillDetailsDAO bddao = new BillDetailsDAO();
                        int success = bdao.insertBill(userID, totalPrice, numOfProduct, dateTimeStr);
                        if (success == 1) {
                            String billID = bdao.getLastBillIDFromBill();
                            for (Map.Entry<ProductDTO, Integer> entry : products.entrySet()) {
                                ProductDTO key = entry.getKey();
                                Integer value = entry.getValue();
                                bddao.insertBillDetails(billID,key.getProductID(), value);
                                pdao.updateQuantity(key.getProductID(), value);
                            }
                            session.removeAttribute("CUSTCART");
                            session.removeAttribute("NUM_OF_PRODUCT");
                            url = PAYMENT_SUCCESSFUL_PAGE;
                            request.setAttribute("PAYMENT_SUCCESS", "true");

                        }
                    }
                }

            }
        } catch (SQLException ex) {
            log.error("PaymentByCast_SQL:"+ex.getMessage());
        } catch (NamingException ex) {
            log.error("PaymentByCast_Naming:"+ex.getMessage());
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
