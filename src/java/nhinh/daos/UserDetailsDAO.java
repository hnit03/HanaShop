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
import nhinh.dtos.UserDetailsDTO;
import nhinh.utils.DBHelper;

/**
 *
 * @author PC
 */
public class UserDetailsDAO implements Serializable{
    
    public UserDetailsDTO getUserDetails(String userID) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        UserDetailsDTO dto = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select id,userID,fullname,phone,address "
                        + "from UserDetails "
                        + "where userID = (select userID from Registration where userID = ?) ";
                ps = con.prepareStatement(sql);
                ps.setString(1, userID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    String id = rs.getString("id");
                    String uID = rs.getString("userID");
                    String fullname = rs.getString("fullname");
                    int phone = rs.getInt("phone");
                    String address = rs.getString("address");
                    dto = new UserDetailsDTO(id,uID, fullname, phone, address);
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
        return dto;
    }
    
    public boolean insertUserDetails(String userID, String fullname, int phone, String address)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps= null;

        String url = "insert into UserDetails(id,userID,fullname,phone,address) "
                + "values(NEWID(),?,?,?,?)";
        try {
            con = DBHelper.makeConnection();

            if (con != null) {
                ps = con.prepareStatement(url);
                ps.setString(1, userID);
                ps.setString(2, fullname);
                ps.setInt(3, phone);
                ps.setString(4, address);
                int count = ps.executeUpdate();
                if (count > 0) {
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
