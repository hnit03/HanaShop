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
public class RegistrationDAO implements Serializable{
    
    public int checkLogin(String userID, String password) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean role = false;
        try {
            con = DBHelper.makeConnection();
            if (con!=null) {
                String sql = "select userID, password, isAdmin "
                        + "from Registration "
                        + "where userID = ? and password = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, userID);
                ps.setString(2, password);
                rs = ps.executeQuery();
                if (rs.next()) {
                    String user = rs.getString("userID");
                    String pass = rs.getString("password");
                    if (user.equals(userID)&&pass.equals(password)) {
                        role = rs.getBoolean("isAdmin");
                        if (role) {
                            return 1;
                        }else{
                            return 0;
                        }
                    }
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
        return -1;
    }
    
    public int checkLoginWithGoogle(String userID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean role = false;
        try {
            con = DBHelper.makeConnection();
            if (con!=null) {
                String sql = "select userID, password, isAdmin "
                        + "from Registration "
                        + "where userID = ? ";
                ps = con.prepareStatement(sql);
                ps.setString(1, userID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    String user = rs.getString("userID");
                    if (user.equals(userID)) {
                        role = rs.getBoolean("isAdmin");
                        if (role) {
                            return 1;
                        }else{
                            return 0;
                        }
                    }
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
        return -1;
    }
    public boolean getIsAdmin(String userID) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean role = false;
        try {
            con = DBHelper.makeConnection();
            if (con!=null) {
                String sql = "select isAdmin "
                        + "from Registration "
                        + "where userID = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, userID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    role = rs.getBoolean(1);
                }
            }
        } finally {
            if (rs!=null) {
                rs.close();
            }
            if (ps!=null) {
                ps.close();
            }
            if (con!=null) {
                con.close();
            }
        }
        return role;
    }
    
    
    
    public String getFullname(String userID) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String fullname = "";
        try {
            con = DBHelper.makeConnection();
            if (con!=null) {
                String sql = "select fullname "
                        + "from Registration "
                        + "where userID = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, userID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    fullname = rs.getString(1);
                }
            }
        } finally {
            if (rs!=null) {
                rs.close();
            }
            if (ps!=null) {
                ps.close();
            }
            if (con!=null) {
                con.close();
            }
        }
        return fullname;
    }
}
