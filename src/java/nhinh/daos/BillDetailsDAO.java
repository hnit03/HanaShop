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
    public boolean insertBillDetails(int billID, int productID, int quantity) throws SQLException, NamingException {
        //1.connect DB
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBHelper.makeConnection();
            //2.tao lenh truy vna61
            if (con != null) {
                String sql = "insert into BillDetails(BillID,productID,Quantity) "
                        + "values(?,?,?)";
                //3.tao doi tuong thuc hien truy van (PrepareST)
                ps = con.prepareStatement(sql);
                ps.setInt(1, billID);
                ps.setInt(2, productID);
                ps.setInt(3, quantity);
                //4.execute
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
