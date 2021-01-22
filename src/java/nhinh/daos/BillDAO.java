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
import javax.naming.NamingException;
import nhinh.utils.DBHelper;

/**
 *
 * @author PC
 */
public class BillDAO implements Serializable{
   public int insertBill(int billID, String userID, float totalPrice, int numOfProduct, String dateTime) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        int result = 0;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO Bill(BillID,userID,TotalPrice,numOfProduct,orderTime) "
                        + "VALUES(?,?,?,?,?);";
                ps = con.prepareStatement(sql);
                ps.setInt(1, billID);
                ps.setString(2, userID);
                ps.setFloat(3, totalPrice);   
                ps.setInt(4, numOfProduct); 
                ps.setString(5, dateTime); 
                result = ps.executeUpdate();
            }
            
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }
    public int getLastBillIDFromBill() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Select top 1 BillID "
                        + "from Bill "
                        + "order by billID desc";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    return rs.getInt("BillID");
                }

            }
        } finally{
            if(rs!=null){
                rs.close();
            }
            if(ps!=null){
                ps.close();
            }
            if (con!=null) {
                con.close();
            }
        }
        return -1;
    } 
}
