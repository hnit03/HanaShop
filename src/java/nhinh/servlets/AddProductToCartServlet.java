/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhinh.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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

/**
 *
 * @author PC
 */
@WebServlet(name = "AddProductToCartServlet", urlPatterns = {"/AddProductToCartServlet"})
public class AddProductToCartServlet extends HttpServlet {

    private final String START_UP_CONTROLLER = "StartUpServlet";
    private final String LOGIN_PAGE = "login.jsp";
    private final String VIEW_CART_PAGE = "viewCart.jsp";
    private final String ERROR_PAGE = "error.jsp";

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
        String productID = request.getParameter("productID");
        String amountStr = request.getParameter("txtAmount");
        String url = ERROR_PAGE;
        int amount = 0;
        int numOfProduct = 0;
        try {
            /* TODO output your page here. You may use following sample code. */
            if (productID != null) {
                if (amountStr != null && amountStr.matches("[0-9]+")) {
                    amount = Integer.parseInt(amountStr);
                }
                HttpSession session = request.getSession(false);
                String fullname = (String) session.getAttribute("FULLNAME");
                if (fullname != null) {
                    ProductDAO dao = new ProductDAO();
                    ProductDTO dto = dao.getProductDTO(productID);
                    int quantityInStock = dao.getQuantityProduct(productID);
                    boolean isAdmin = (boolean) session.getAttribute("ISADMIN");
                    if (isAdmin) {
                        url = START_UP_CONTROLLER;
                    } else {
                        CartObject cart = (CartObject) session.getAttribute("CUSTCART");
                        Object temp = (Object) session.getAttribute("NUM_OF_PRODUCT");
                        if (temp != null) {
                            numOfProduct = (int) temp;
                        }
                        if (cart == null) {
                            cart = new CartObject();
                            if (amount <= quantityInStock) {
                                cart.addProductToCart(dto, amount);
                                session.setAttribute("CUSTCART", cart);
                                request.setAttribute("ADD_SUCCESS", true);
                                numOfProduct += 1;
                                session.setAttribute("NUM_OF_PRODUCT", numOfProduct);
                                url = START_UP_CONTROLLER;
                            }
                        } else {
                            String plus = request.getParameter("plus");
                            int quantity = 0;
                            for (ProductDTO pdto : cart.getProducts().keySet()) {
                                if (productID.equals(pdto.getProductID())) {
                                    quantity = cart.getProducts().get(pdto);
                                    break;
                                }
                            }

                            if (quantity > quantityInStock) {
                                request.setAttribute("OUT_OF_STOCK", "true");
                                request.setAttribute("QUANTITY_IN_STOCK", quantityInStock);
                                for (ProductDTO pdto : cart.getProducts().keySet()) {
                                    if (productID.equals(pdto.getProductID())) {
                                        cart.getProducts().replace(pdto, quantityInStock);
                                        break;
                                    }
                                }

                                url = VIEW_CART_PAGE;
                            } else {
                                if (plus != null) {
                                    cart.increaseProductToCart(dto.getProductID());
                                    url = VIEW_CART_PAGE;
                                } else {
                                    if (amount <= quantityInStock) {
                                        int amountInCart = 0;
                                        for (Map.Entry<ProductDTO, Integer> en : cart.getProducts().entrySet()) {
                                            if (en.getKey().getProductID().equals(productID)) {
                                                amountInCart = en.getValue();
                                                break;
                                            }
                                        }
                                        int totalAmount = amountInCart + amount;
                                        if (totalAmount <= quantityInStock) {
                                            cart.addProductToCart(dto, amount);
                                            session.setAttribute("CUSTCART", cart);
                                            request.setAttribute("ADD_SUCCESS", true);
                                            numOfProduct += 1;
                                            session.setAttribute("NUM_OF_PRODUCT", numOfProduct);
                                            url = START_UP_CONTROLLER;
                                        }else{
                                            request.setAttribute("MAXIMUM_AMOUNT", "The " + dto.getProductName() + " was added in maximum quantity.");
                                            url = "DispatchServlet?btnAction=More&productID="+productID;
                                        }

                                    }
                                }
                            }
                        }
                    }

                } else {
                    url = LOGIN_PAGE;
                }
            }
        } catch (SQLException ex) {
            log("AddProductToCart_SQL:" + ex.getMessage());
        } catch (NamingException ex) {
            log("AddProductToCart_Naming:" + ex.getMessage());
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
