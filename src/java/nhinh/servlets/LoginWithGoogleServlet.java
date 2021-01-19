/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhinh.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import nhinh.accessGoogle.GooglePojo;
import nhinh.accessGoogle.GoogleUtils;
import nhinh.daos.RegistrationDAO;
import nhinh.daos.UserDetailsDAO;
import nhinh.dtos.UserDetailsDTO;

/**
 *
 * @author PC
 */
@WebServlet(name = "LoginWithGoogleServlet", urlPatterns = {"/LoginWithGoogleServlet"})
public class LoginWithGoogleServlet extends HttpServlet {

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
        String url = "login.jsp";
        try {
            /* TODO output your page here. You may use following sample code. */
            String code = request.getParameter("code");
            if (code != null) {
                HttpSession session = request.getSession();
                String accessToken = GoogleUtils.getToken(code);
                GooglePojo googlePojo = GoogleUtils.getUserInfo(accessToken);
                String userID = googlePojo.getEmail();
                RegistrationDAO dao = new RegistrationDAO();
                int role = dao.checkLoginWithGoogle(userID);
                
                boolean isAdmin = false;
                if (role != -1) {
                    if (role == 0) {
                        url = "UserStartUpServlet";
                        UserDetailsDAO udao = new UserDetailsDAO();
                        UserDetailsDTO udto = udao.getUserDetails(userID);
                        session.setAttribute("USER_DETAILS", udto);
                        session.setAttribute("USERID", userID);
                        isAdmin = false;
                    } else {
                        url = "AdminStartUpServlet";
                        isAdmin = true;
                    }
                    session.setAttribute("USERID", userID);
                    session.setAttribute("FULLNAME", dao.getFullname(userID));
                    session.setAttribute("ISADMIN", isAdmin);
                } 
                
            }

        } catch (SQLException ex) {
            Logger.getLogger(LoginWithGoogleServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(LoginWithGoogleServlet.class.getName()).log(Level.SEVERE, null, ex);
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