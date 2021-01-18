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
import nhinh.dtos.BillDTO;
import nhinh.dtos.BillDetailsDTO;
import nhinh.dtos.ProductDTO;

/**
 *
 * @author PC
 */
@WebServlet(name = "CheckoutServlet", urlPatterns = {"/CheckoutServlet"})
public class CheckoutServlet extends HttpServlet {

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
            HttpSession session = request.getSession(false);
            if (session != null) {
                String userID = (String) session.getAttribute("USERID");
                CartObject cart = (CartObject) session.getAttribute("CUSTCART");
                if (cart != null) {
                    Map<ProductDTO, Integer> products = cart.getProducts();
                    if (products != null) {
                        String[] productIDStr = request.getParameterValues("productID");
                        String[] quantityStr = request.getParameterValues("txtAmount");
                        float totalPrice = Float.parseFloat(request.getParameter("txtTotalPrice"));
                        session.setAttribute("TOTAL_PRICE", totalPrice);
                        BillDAO bdao = new BillDAO();
                        int billID = bdao.getLastBillIDFromBill();
                        if (billID < 0) {
                            billID = 10000;
                        } else {
                            billID = billID + 1;
                        }
                        BillDTO bdto = new BillDTO(billID, userID, totalPrice);
                        session.setAttribute("BILL", bdto);
                        List<BillDetailsDTO> list = new ArrayList<>();
                        int count = 0;
                        int productID = 0;
                        int quantity = 0;
                        while (count < productIDStr.length && count < quantityStr.length) {
                            productID = Integer.parseInt(productIDStr[count]);
                            quantity = Integer.parseInt(quantityStr[count]);
                            BillDetailsDTO bddto = new BillDetailsDTO(billID, productID, quantity);
                            list.add(bddto);
                            count++;
                        }
                        session.setAttribute("BILL_LIST", list);
                        
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CheckoutServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(CheckoutServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            String url = "information.jsp";
            response.sendRedirect(url);
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
