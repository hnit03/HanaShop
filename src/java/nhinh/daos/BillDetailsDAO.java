/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhinh.daos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.NamingException;
import nhinh.utils.DBHelper;

/**
 *
 * @author PC
 */
public class BillDetailsDAO implements Serializable{
    public boolean insertBillDetails(String billID,String productID, int quantity) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "insert into BillDetails(bdID,BillID,productID,Quantity) "
                        + "values(NEWID(),?,?,?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, billID);
                ps.setString(2, productID);
                ps.setInt(3, quantity);
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
}
