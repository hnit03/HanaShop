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
import nhinh.dtos.ProductDTO;
import nhinh.utils.DBHelper;

/**
 *
 * @author PC
 */
public class ProductDAO implements Serializable {

    private final int RECORDS_IN_PAGE = 20;
    private List<ProductDTO> productList;

    public List<ProductDTO> getProductList() {
        return productList;
    }

    public int getLastProduct() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int productID = 0;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select productID "
                        + "from Product "
                        + "order by productID desc";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    productID = rs.getInt("productID");

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
        return productID;
    }

    public ProductDTO getProductDTO(String pID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ProductDTO dto = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select productID,productName,image,description,price,createDate,categoryID,status,quantity "
                        + "from Product "
                        + "where productID = ? and status = 1 ";
                ps = con.prepareStatement(sql);
                ps.setString(1, pID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int productID = rs.getInt("productID");
                    String productName = rs.getString("productName");
                    String image = rs.getString("image");
                    String description = rs.getString("description");
                    float price = rs.getFloat("price");
                    String createDate = rs.getString("createDate");
                    String categoryID = rs.getString("categoryID");
                    CategoryDAO dao = new CategoryDAO();
                    dao.getAllCategory();
                    CategoryDTO cdto = dao.findCategoryDTO(categoryID);
                    boolean status = rs.getBoolean("status");
                    int quantity = rs.getInt("quantity");
                    dto = new ProductDTO(productID, productName, image, description, price, createDate, cdto, status, quantity);
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

    public ProductDTO getProductDTOByAdmin(int pID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ProductDTO dto = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select productID,productName,image,description,price,createDate,categoryID,status,quantity "
                        + "from Product "
                        + "where productID = ? ";
                ps = con.prepareStatement(sql);
                ps.setInt(1, pID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int productID = rs.getInt("productID");
                    String productName = rs.getString("productName");
                    String image = rs.getString("image");
                    String description = rs.getString("description");
                    float price = rs.getFloat("price");
                    String createDate = rs.getString("createDate");
                    String categoryID = rs.getString("categoryID");
                    CategoryDAO dao = new CategoryDAO();
                    dao.getAllCategory();
                    CategoryDTO cdto = dao.findCategoryDTO(categoryID);
                    boolean status = rs.getBoolean("status");
                    int quantity = rs.getInt("quantity");
                    dto = new ProductDTO(productID, productName, image, description, price, createDate, cdto, status, quantity);
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

    public void getAllActiveProducts(int pageNo) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT productID,productName,image,description,price,createDate,categoryID,status,quantity "
                        + "FROM Product "
                        + "WHERE status = 1 "
                        + "ORDER BY createDate ASC "
                        + "OFFSET ? ROWS "
                        + "FETCH NEXT ? ROWS ONLY";
                ps = con.prepareStatement(sql);
                int dismissRecord = (pageNo - 1) * RECORDS_IN_PAGE;
                ps.setInt(1, dismissRecord);
                ps.setInt(2, RECORDS_IN_PAGE);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int productID = rs.getInt("productID");
                    String productName = rs.getString("productName");
                    String image = rs.getString("image");
                    String description = rs.getString("description");
                    float price = rs.getFloat("price");
                    String createDate = rs.getString("createDate");
                    String categoryID = rs.getString("categoryID");
                    CategoryDAO dao = new CategoryDAO();
                    dao.getAllCategory();
                    CategoryDTO cdto = dao.findCategoryDTO(categoryID);
                    boolean status = rs.getBoolean("status");
                    int quantity = rs.getInt("quantity");
                    ProductDTO dto = new ProductDTO(productID, productName, image, description, price, createDate, cdto, status, quantity);
                    if (this.productList == null) {
                        this.productList = new ArrayList<>();
                    }
                    this.productList.add(dto);
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

    public void getAllProducts(int pageNo) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT productID,productName,image,description,price,createDate,categoryID,status,quantity "
                        + "FROM Product "
                        + "ORDER BY createDate ASC "
                        + "OFFSET ? ROWS "
                        + "FETCH NEXT ? ROWS ONLY";
                ps = con.prepareStatement(sql);
                int dismissRecord = (pageNo - 1) * RECORDS_IN_PAGE;
                ps.setInt(1, dismissRecord);
                ps.setInt(2, RECORDS_IN_PAGE);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int productID = rs.getInt("productID");
                    String productName = rs.getString("productName");
                    String image = rs.getString("image");
                    String description = rs.getString("description");
                    float price = rs.getFloat("price");
                    String createDate = rs.getString("createDate");
                    String categoryID = rs.getString("categoryID");
                    CategoryDAO dao = new CategoryDAO();
                    dao.getAllCategory();
                    CategoryDTO cdto = dao.findCategoryDTO(categoryID);
                    boolean status = rs.getBoolean("status");
                    int quantity = rs.getInt("quantity");
                    ProductDTO dto = new ProductDTO(productID, productName, image, description, price, createDate, cdto, status, quantity);
                    if (this.productList == null) {
                        this.productList = new ArrayList<>();
                    }
                    this.productList.add(dto);
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

    public void userSearchProductByPrice(int priceMin, int priceMax, int pageNo) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select productID,productName,image,description,price,createDate,categoryID,status,quantity "
                        + "from Product "
                        + "where price >= ? and price <= ? and status = 1 "
                        + "ORDER BY createDate ASC "
                        + "OFFSET ? ROWS "
                        + "FETCH NEXT ? ROWS ONLY";
                ps = con.prepareStatement(sql);
                ps.setInt(1, priceMin);
                ps.setInt(2, priceMax);
                int dismissRecord = (pageNo - 1) * RECORDS_IN_PAGE;
                ps.setInt(3, dismissRecord);
                ps.setInt(4, RECORDS_IN_PAGE);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int productID = rs.getInt("productID");
                    String productName = rs.getString("productName");
                    String image = rs.getString("image");
                    String description = rs.getString("description");
                    float price = rs.getFloat("price");
                    String createDate = rs.getString("createDate");
                    String categoryID = rs.getString("categoryID");
                    CategoryDAO dao = new CategoryDAO();
                    dao.getAllCategory();
                    CategoryDTO cdto = dao.findCategoryDTO(categoryID);
                    boolean status = rs.getBoolean("status");
                    int quantity = rs.getInt("quantity");
                    ProductDTO dto = new ProductDTO(productID, productName, image, description, price, createDate, cdto, status, quantity);
                    if (this.productList == null) {
                        this.productList = new ArrayList<>();
                    }
                    this.productList.add(dto);
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

    public void adminSearchProductByPrice(int priceMin, int priceMax, int pageNo) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select productID,productName,image,description,price,createDate,categoryID,status,quantity "
                        + "from Product "
                        + "where price >= ? and price <= ? "
                        + "ORDER BY createDate ASC "
                        + "OFFSET ? ROWS "
                        + "FETCH NEXT ? ROWS ONLY";
                ps = con.prepareStatement(sql);
                ps.setInt(1, priceMin);
                ps.setInt(2, priceMax);
                int dismissRecord = (pageNo - 1) * RECORDS_IN_PAGE;
                ps.setInt(3, dismissRecord);
                ps.setInt(4, RECORDS_IN_PAGE);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int productID = rs.getInt("productID");
                    String productName = rs.getString("productName");
                    String image = rs.getString("image");
                    String description = rs.getString("description");
                    float price = rs.getFloat("price");
                    String createDate = rs.getString("createDate");
                    String categoryID = rs.getString("categoryID");
                    CategoryDAO dao = new CategoryDAO();
                    dao.getAllCategory();
                    CategoryDTO cdto = dao.findCategoryDTO(categoryID);
                    boolean status = rs.getBoolean("status");
                    int quantity = rs.getInt("quantity");
                    ProductDTO dto = new ProductDTO(productID, productName, image, description, price, createDate, cdto, status, quantity);
                    if (this.productList == null) {
                        this.productList = new ArrayList<>();
                    }
                    this.productList.add(dto);
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

    public void userSearchProductByCategory(String categotyName, int priceMin, int priceMax, int pageNo) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select productID,productName,image,description,price,createDate,categoryID,status,quantity "
                        + "from Product "
                        + "where categoryID = "
                        + "(select categoryID from Category where categoryName = ?) "
                        + "and price >= ? and price <= ? "
                        + "and status = 1 "
                        + "ORDER BY createDate ASC"
                        + "OFFSET ? ROWS "
                        + "FETCH NEXT ? ROWS ONLY";
                ps = con.prepareStatement(sql);
                ps.setString(1, categotyName);
                ps.setInt(2, priceMin);
                ps.setInt(3, priceMax);
                int dismissRecord = (pageNo - 1) * RECORDS_IN_PAGE;
                ps.setInt(4, dismissRecord);
                ps.setInt(5, RECORDS_IN_PAGE);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int productID = rs.getInt("productID");
                    String productName = rs.getString("productName");
                    String image = rs.getString("image");
                    String description = rs.getString("description");
                    float price = rs.getFloat("price");
                    String createDate = rs.getString("createDate");
                    String categoryID = rs.getString("categoryID");
                    CategoryDAO dao = new CategoryDAO();
                    dao.getAllCategory();
                    CategoryDTO cdto = dao.findCategoryDTO(categoryID);
                    boolean status = rs.getBoolean("status");
                    int quantity = rs.getInt("quantity");
                    ProductDTO dto = new ProductDTO(productID, productName, image, description, price, createDate, cdto, status, quantity);
                    if (this.productList == null) {
                        this.productList = new ArrayList<>();
                    }
                    this.productList.add(dto);
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

    public void adminSearchProductByCategory(String categotyName, int priceMin, int priceMax, int pageNo) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select productID,productName,image,description,price,createDate,categoryID,status,quantity "
                        + "from Product "
                        + "where categoryID = "
                        + "(select categoryID from Category where categoryName = ?) "
                        + "and price >= ? and price <= ? "
                        + "ORDER BY createDate ASC "
                        + "OFFSET ? ROWS "
                        + "FETCH NEXT ? ROWS ONLY";
                ps = con.prepareStatement(sql);
                ps.setString(1, categotyName);
                ps.setInt(2, priceMin);
                ps.setInt(3, priceMax);
                int dismissRecord = (pageNo - 1) * RECORDS_IN_PAGE;
                ps.setInt(4, dismissRecord);
                ps.setInt(5, RECORDS_IN_PAGE);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int productID = rs.getInt("productID");
                    String productName = rs.getString("productName");
                    String image = rs.getString("image");
                    String description = rs.getString("description");
                    float price = rs.getFloat("price");
                    String createDate = rs.getString("createDate");
                    String categoryID = rs.getString("categoryID");
                    CategoryDAO dao = new CategoryDAO();
                    dao.getAllCategory();
                    CategoryDTO cdto = dao.findCategoryDTO(categoryID);
                    boolean status = rs.getBoolean("status");
                    int quantity = rs.getInt("quantity");
                    ProductDTO dto = new ProductDTO(productID, productName, image, description, price, createDate, cdto, status, quantity);
                    if (this.productList == null) {
                        this.productList = new ArrayList<>();
                    }
                    this.productList.add(dto);
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

    public void userSearchProductByName(String pName, int priceMin, int priceMax, int pageNo) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select productID,productName,image,description,price,createDate,categoryID,status,quantity "
                        + "from Product "
                        + "where productName like ? "
                        + "and price >= ? and price <= ? "
                        + "and status = 1 "
                        + "ORDER BY createDate ASC "
                        + "OFFSET ? ROWS "
                        + "FETCH NEXT ? ROWS ONLY";
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + pName + "%");
                ps.setInt(2, priceMin);
                ps.setInt(3, priceMax);
                int dismissRecord = (pageNo - 1) * RECORDS_IN_PAGE;
                ps.setInt(4, dismissRecord);
                ps.setInt(5, RECORDS_IN_PAGE);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int productID = rs.getInt("productID");
                    String productName = rs.getString("productName");
                    String image = rs.getString("image");
                    String description = rs.getString("description");
                    float price = rs.getFloat("price");
                    String createDate = rs.getString("createDate");
                    String categoryID = rs.getString("categoryID");
                    CategoryDAO dao = new CategoryDAO();
                    dao.getAllCategory();
                    CategoryDTO cdto = dao.findCategoryDTO(categoryID);
                    boolean status = rs.getBoolean("status");
                    int quantity = rs.getInt("quantity");
                    ProductDTO dto = new ProductDTO(productID, productName, image, description, price, createDate, cdto, status, quantity);
                    if (this.productList == null) {
                        this.productList = new ArrayList<>();
                    }
                    this.productList.add(dto);
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

    public void adminSearchProductByName(String pName, int priceMin, int priceMax, int pageNo) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select productID,productName,image,description,price,createDate,categoryID,status,quantity "
                        + "from Product "
                        + "where productName like ? "
                        + "and price >= ? and price <= ? "
                        + "ORDER BY createDate ASC "
                        + "OFFSET ? ROWS "
                        + "FETCH NEXT ? ROWS ONLY";
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + pName + "%");
                ps.setInt(2, priceMin);
                ps.setInt(3, priceMax);
                int dismissRecord = (pageNo - 1) * RECORDS_IN_PAGE;
                ps.setInt(4, dismissRecord);
                ps.setInt(5, RECORDS_IN_PAGE);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int productID = rs.getInt("productID");
                    String productName = rs.getString("productName");
                    String image = rs.getString("image");
                    String description = rs.getString("description");
                    float price = rs.getFloat("price");
                    String createDate = rs.getString("createDate");
                    String categoryID = rs.getString("categoryID");
                    CategoryDAO dao = new CategoryDAO();
                    dao.getAllCategory();
                    CategoryDTO cdto = dao.findCategoryDTO(categoryID);
                    boolean status = rs.getBoolean("status");
                    int quantity = rs.getInt("quantity");
                    ProductDTO dto = new ProductDTO(productID, productName, image, description, price, createDate, cdto, status, quantity);
                    if (this.productList == null) {
                        this.productList = new ArrayList<>();
                    }
                    this.productList.add(dto);
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

    public void userSearchProductByAll(String pName, String categotyName, int priceMin, int priceMax, int pageNo) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select productID,productName,image,description,price,createDate,categoryID,status,quantity "
                        + "from Product "
                        + "where productName like ? "
                        + "and categoryID = "
                        + "(select categoryID from Category where categoryName = ?) "
                        + "and price >= ? and price <= ? and status = 1 "
                        + "ORDER BY createDate ASC "
                        + "OFFSET ? ROWS "
                        + "FETCH NEXT ? ROWS ONLY";
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + pName + "%");
                ps.setString(2, categotyName);
                ps.setInt(3, priceMin);
                ps.setInt(4, priceMax);
                int dismissRecord = (pageNo - 1) * RECORDS_IN_PAGE;

                ps.setInt(5, dismissRecord);
                ps.setInt(6, RECORDS_IN_PAGE);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int productID = rs.getInt("productID");
                    String productName = rs.getString("productName");
                    String image = rs.getString("image");
                    String description = rs.getString("description");
                    float price = rs.getFloat("price");
                    String createDate = rs.getString("createDate");
                    String categoryID = rs.getString("categoryID");
                    CategoryDAO dao = new CategoryDAO();
                    dao.getAllCategory();
                    CategoryDTO cdto = dao.findCategoryDTO(categoryID);
                    boolean status = rs.getBoolean("status");
                    int quantity = rs.getInt("quantity");
                    ProductDTO dto = new ProductDTO(productID, productName, image, description, price, createDate, cdto, status, quantity);
                    if (this.productList == null) {
                        this.productList = new ArrayList<>();
                    }
                    this.productList.add(dto);
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

    public void adminSearchProductByAll(String pName, String categotyName, int priceMin, int priceMax, int pageNo) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select productID,productName,image,description,price,createDate,categoryID,status,quantity "
                        + "from Product "
                        + "where productName like ? "
                        + "and categoryID = "
                        + "(select categoryID from Category where categoryName = ?) "
                        + "and price >= ? and price <= ? "
                        + "ORDER BY createDate ASC "
                        + "OFFSET ? ROWS "
                        + "FETCH NEXT ? ROWS ONLY";
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + pName + "%");
                ps.setString(2, categotyName);
                ps.setInt(3, priceMin);
                ps.setInt(4, priceMax);
                int dismissRecord = (pageNo - 1) * RECORDS_IN_PAGE;

                ps.setInt(5, dismissRecord);
                ps.setInt(6, RECORDS_IN_PAGE);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int productID = rs.getInt("productID");

                    String productName = rs.getString("productName");
                    String image = rs.getString("image");
                    String description = rs.getString("description");
                    float price = rs.getFloat("price");
                    String createDate = rs.getString("createDate");
                    String categoryID = rs.getString("categoryID");
                    CategoryDAO dao = new CategoryDAO();
                    dao.getAllCategory();
                    CategoryDTO cdto = dao.findCategoryDTO(categoryID);
                    boolean status = rs.getBoolean("status");
                    int quantity = rs.getInt("quantity");
                    ProductDTO dto = new ProductDTO(productID, productName, image, description, price, createDate, cdto, status, quantity);
                    if (this.productList == null) {
                        this.productList = new ArrayList<>();
                    }
                    this.productList.add(dto);
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

    public boolean createNewProduct(ProductDTO dto) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "insert into Product(productID,productName,image,description,price,createDate,categoryID,status,quantity) "
                        + "values(?,?,?,?,?,?,?,?,?)";
                ps = con.prepareStatement(sql);
                ps.setInt(1, dto.getProductID());
                ps.setString(2, dto.getProductName());
                ps.setString(3, dto.getImage());
                ps.setString(4, dto.getDescription());
                ps.setFloat(5, dto.getPrice());
                ps.setString(6, dto.getCreateDate());
                ps.setString(7, dto.getCdto().getCategoryID());
                ps.setBoolean(8, dto.isStatus());
                ps.setInt(9, dto.getQuantity());
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

    public boolean updateProduct(ProductDTO dto) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "update Product "
                        + "set productName = ?,image = ?, description = ?, price = ?, createDate = ?, "
                        + "categoryID = ?, status = ?, quantity = ? "
                        + "where productID = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, dto.getProductName());
                System.out.println(dto.getProductName());
                ps.setString(2, dto.getImage());
                System.out.println(dto.getImage());
                ps.setString(3, dto.getDescription());
                System.out.println(dto.getDescription());
                ps.setFloat(4, dto.getPrice());
                System.out.println(dto.getPrice());
                ps.setString(5, dto.getCreateDate());
                System.out.println(dto.getCreateDate());
                ps.setString(6, dto.getCdto().getCategoryID());
                System.out.println(dto.getCdto().getCategoryID());
                ps.setBoolean(7, dto.isStatus());
                System.out.println(dto.isStatus());
                ps.setInt(8, dto.getQuantity());
                System.out.println(dto.getQuantity());
                ps.setInt(9, dto.getProductID());
                System.out.println(dto.getProductID());
                int row = ps.executeUpdate();
                System.out.println("row " + row);
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

    public int getQuantityProduct(String productName)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "select Quantity from Product "
                + "where productName=?";
        try {
            con = DBHelper.makeConnection();

            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, productName);
                rs = ps.executeQuery();
                if (rs.next()) {
                    return rs.getInt("Quantity");
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

    public boolean updateQuantity(String productName, int decrease) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "update Product "
                        + "set quantity = ? "
                        + "where productName = ?";
                ps = con.prepareStatement(sql);
                int totalQuantity = getQuantityProduct(productName);
                ps.setInt(1, (totalQuantity - decrease));
                ps.setString(2, productName);
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

    public boolean updateQuickly(int productID, String categoryID, boolean status) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "update Product "
                        + "set categoryID = ?, status = ? "
                        + "where productID = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, categoryID);
                ps.setBoolean(2, status);
                ps.setInt(3, productID);
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

    public boolean checkDupName(String productName) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select productName "
                        + "from Product "
                        + "where productName = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, productName);
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
