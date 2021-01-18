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
import nhinh.dtos.CategoryDTO;
import nhinh.utils.DBHelper;

/**
 *
 * @author PC
 */
public class CategoryDAO implements Serializable {

    private List<CategoryDTO> categoryList;

    public List<CategoryDTO> getCategoryList() {
        return categoryList;
    }

    public int find(String categoryID) {
        for (int i = 0; i < categoryList.size(); i++) {
            if (categoryID.equals(categoryList.get(i).getCategoryID())) {
                return i;
            }
        }
        return -1;
    }

    public CategoryDTO findCategoryDTO(String categoryID) {
        int i = find(categoryID);
        return i < 0 ? null : categoryList.get(i);
    }

    public CategoryDTO getCategoryDTO(String cName) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        CategoryDTO dto = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select categoryID,categoryName "
                        + "from Category "
                        + "where categoryName = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, cName);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String categoryID = rs.getString(1);
                    String categoryName = rs.getString(2);
                    dto = new CategoryDTO(categoryID, categoryName);
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
    
    public void getAllCategory() throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select categoryID,categoryName "
                        + "from Category ";
                ps = con.prepareStatement(sql);

                rs = ps.executeQuery();
                //5.xu lý kết qua
                while (rs.next()) {
                    String categoryID = rs.getString(1);
                    String categoryName = rs.getString(2);
                    CategoryDTO dto = new CategoryDTO(categoryID, categoryName);
                    if (this.categoryList == null) {
                        this.categoryList = new ArrayList<>();
                    }
                    this.categoryList.add(dto);
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
}
