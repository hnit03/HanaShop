/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhinh.daos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import nhinh.dtos.ProductDTO;
import nhinh.dtos.ProductHistoryDTO;
import nhinh.utils.DBHelper;

/**
 *
 * @author PC
 */
public class ProductHistoryDAO implements Serializable{
    public boolean insertAction(ProductDTO dTO,String userID, String action, String date) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBHelper.makeConnection();
            if (con!=null) {
                String sql = "Insert into ProductHistory(HistoryID,productID,userID,action,date) "
                        + "values(NEWID(),?,?,?,?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, dTO.getProductID());
                ps.setString(2, userID);
                ps.setString(3, action);
                ps.setString(4, date);
                int row = ps.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
    private List<ProductHistoryDTO> productHistoryList;

    public List<ProductHistoryDTO> getProductHistoryList() {
        return productHistoryList;
    }
    public void getAllProductHistory(String uID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs =null;
        ProductDAO dao = new ProductDAO();
        try {
            con = DBHelper.makeConnection();
            if (con!=null) {
                String sql = "select historyID, productID, userID, action, date "
                        + "from ProductHistory "
                        + "where userID = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, uID);
                rs = ps.executeQuery();
                
                while (rs.next()) {
                    String historyID = rs.getString("historyID");
                    String productID = rs.getString("productID");
                    ProductDTO pdto = dao.getProductDTO(productID);
                    String userID = rs.getString("userID");
                    String action = rs.getString("action");
                    String date = rs.getString("date");
                    ProductHistoryDTO phdto = new ProductHistoryDTO(historyID, pdto, userID, action, date);
                    if (this.productHistoryList == null) {
                        this.productHistoryList = new ArrayList<>();
                    }
                    productHistoryList.add(phdto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
    public int getLastHistory() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int historyID = 0;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select top 1 historyID "
                        + "from ProductHistory "
                        + "order by date desc";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    historyID = rs.getInt("historyID");
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return historyID;
    }
}
