/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhinh.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
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
@WebServlet(name = "PaymentByCastServlet", urlPatterns = {"/PaymentByCastServlet"})
public class PaymentByCastServlet extends HttpServlet {

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
        try {
            /* TODO output your page here. You may use following sample code. */
            String fullname = (String) request.getAttribute("FULLNAME_PAYMENT_REQ");
            String phoneStr = (String) request.getAttribute("PHONE_PAYMENT_REQ");
            String address = (String) request.getAttribute("ADDRESS_PAYMENT_REQ");
            String totalPriceStr = (String) request.getAttribute("TOTAL_PRICE_PAYMENT_REQ");
            //check null
            if (fullname != null && phoneStr != null && address != null && totalPriceStr !=null) {
                int phone = Integer.parseInt(phoneStr);
                HttpSession session = request.getSession(false);
                // get cart
                CartObject cart = (CartObject) session.getAttribute("CUSTCART");
                Map<ProductDTO, Integer> products = cart.getProducts();
                int numOfProduct = 1;
                Date dateTime = new Date();
                //check products not equal null
                if (products != null) {
                    float totalPrice = Float.parseFloat(totalPriceStr);
//                    session.setAttribute("TOTAL_PRICE", totalPrice);
                    Object numObj = session.getAttribute("NUM_OF_PRODUCT");
                    if (numObj != null) {
                        numOfProduct = (int) numObj;
                    }
                    // create bill
                    BillDAO bdao = new BillDAO();
                    int billID = bdao.getLastBillIDFromBill();
                    if (billID < 0) {
                        billID = 10000;
                    } else {
                        billID = billID + 1;
                    }
                    String userID = (String) session.getAttribute("USERID");
                    
                    Utils utils = new Utils();
                    String dateTimeStr = utils.formatDateTimeToString(dateTime);
                    
                    BillDTO bdto = new BillDTO(billID, userID, totalPrice, numOfProduct, dateTimeStr);
                    UserDetailsDAO uddao = new UserDetailsDAO();
                    int id = uddao.getLastIDFromUD();
                    if (id < 0) {
                        id = 10000;
                    } else {
                        id = id + 1;
                    }
                    
                    //insert user detail
                    UserDetailsDTO uddto = new UserDetailsDTO(id, userID, fullname, phone, address);
                    uddao.insertUserDetails(uddto);
                    
                    if (bdto != null) {
                        int success = bdao.insertBill(bdto.getBillID(), userID, bdto.getTotalPrice(), bdto.getNumOfProduct(), bdto.getOrderTime());
                        if (success == 1) {
                            List<BillDetailsDTO> list = new ArrayList<>();
                            for (Map.Entry<ProductDTO, Integer> entry : products.entrySet()) {
                                ProductDTO key = entry.getKey();
                                Integer value = entry.getValue();
                                BillDetailsDTO bddto = new BillDetailsDTO(billID, key.getProductID(), value);
                                list.add(bddto);
                            }
                            ProductDAO pdao = new ProductDAO();
                            BillDetailsDAO bddao = new BillDetailsDAO();
                            for (BillDetailsDTO dto : list) {
                                bddao.insertBillDetails(dto.getBillID(), dto.getProductID(), dto.getQuantity());
                                pdao.updateQuantity(dto.getProductID(), dto.getQuantity());
                            }
                            session.removeAttribute("CUSTCART");
                            session.removeAttribute("NUM_OF_PRODUCT");
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PaymentByCastServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(PaymentByCastServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            response.sendRedirect("paymentSuccess.jsp");
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
