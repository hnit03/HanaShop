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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import nhinh.daos.BillDAO;
import nhinh.daos.BillDetailsDAO;
import nhinh.daos.UserDetailsDAO;
import nhinh.dtos.BillDTO;
import nhinh.dtos.BillDetailsDTO;
import nhinh.dtos.UserDetailsDTO;

/**
 *
 * @author PC
 */
@WebServlet(name = "PaymentServlet", urlPatterns = {"/PaymentServlet"})
public class PaymentServlet extends HttpServlet {

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
            String paymentMethod = request.getParameter("cboMethod");
            if (paymentMethod.equals("Momo")) {
//                url = "paymentWithMomo.jsp";
            } else {
                String fullname = request.getParameter("txtFullnameInfo");
                String phoneStr = request.getParameter("txtPhone");
                String address = request.getParameter("txtAddress");
                HttpSession session = request.getSession(false);
                if (session != null) {
                    int phone = Integer.parseInt(phoneStr);
                    BillDTO billDTO = (BillDTO) session.getAttribute("BILL");
                    String userID = (String) session.getAttribute("USERID");
                    UserDetailsDAO uddao = new UserDetailsDAO();
                    int id = uddao.getLastIDFromUD();
                    if (id < 0) {
                        id = 10000;
                    } else {
                        id = id + 1;
                    }
                    UserDetailsDTO uddto = new UserDetailsDTO(id, userID, fullname, phone, address);
                    uddao.insertUserDetails(uddto);
                    if (billDTO != null) {
                        BillDAO bdao = new BillDAO();
                        bdao.insertBill(billDTO.getBillID(), userID, billDTO.getTotalPrice());
                        List<BillDetailsDTO> list = (List<BillDetailsDTO>) session.getAttribute("BILL_LIST");
                        if (list != null) {
                            BillDetailsDAO bddao = new BillDetailsDAO();
                            for (BillDetailsDTO dto : list) {
                                bddao.insertBillDetails(dto.getBillID(), dto.getProductID(), dto.getQuantity());
                            }
                            session.removeAttribute("CUSTCART");
                        }
                    }
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(PaymentServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(PaymentServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            response.sendRedirect("StartUpServlet");
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
