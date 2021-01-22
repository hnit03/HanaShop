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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
                String sql = "select top 1 productID "
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

    public ProductDTO getProductDTO(int pID) throws SQLException, NamingException {
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
                if (this.productList == null) {
                    this.productList = new ArrayList<>();
                }
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

    public void searchProductByAll(String pName, String categoryName, int priceMin, int priceMax, int pageNo) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select productID,productName,image,description,price,createDate,categoryID,status,quantity "
                        + "from Product "
                        + "where productName like ? "
                        + "and categoryID in "
                        + "(select categoryID from Category where categoryName like ?) "
                        + "and price >= ? and price <= ? and status = 1 "
                        + "ORDER BY createDate ASC "
                        + "OFFSET ? ROWS "
                        + "FETCH NEXT ? ROWS ONLY";
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + pName + "%");
                ps.setString(2, "%" + categoryName + "%");
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

    public void searchProductByAllByAdmin(String pName, String categoryName, int priceMin, int priceMax, int pageNo) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select productID,productName,image,description,price,createDate,categoryID,status,quantity "
                        + "from Product "
                        + "where productName like ? "
                        + "and categoryID in "
                        + "(select categoryID from Category where categoryName like ?) "
                        + "and price >= ? and price <= ? "
                        + "ORDER BY createDate ASC "
                        + "OFFSET ? ROWS "
                        + "FETCH NEXT ? ROWS ONLY";
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + pName + "%");
                ps.setString(2, "%" + categoryName + "%");
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
                ps.setString(2, dto.getImage());
                ps.setString(3, dto.getDescription());
                ps.setFloat(4, dto.getPrice());
                ps.setString(5, dto.getCreateDate());
                ps.setString(6, dto.getCdto().getCategoryID());
                ps.setBoolean(7, dto.isStatus());
                ps.setInt(8, dto.getQuantity());
                ps.setInt(9, dto.getProductID());
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

    public int getQuantityProduct(int productID)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "select Quantity from Product "
                + "where productID=?";
        try {
            con = DBHelper.makeConnection();

            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setInt(1, productID);
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

    public boolean updateQuantity(int productID, int decrease) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "update Product "
                        + "set quantity = ? "
                        + "where productID = ?";
                ps = con.prepareStatement(sql);
                int totalQuantity = getQuantityProduct(productID);
                ps.setInt(1, (totalQuantity - decrease));
                ps.setInt(2, productID);
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

    public boolean updateCategory(int productID, String categoryID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "update Product "
                        + "set categoryID = ? "
                        + "where productID = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, categoryID);
                ps.setInt(2, productID);
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
    public boolean updateStatus(int productID, boolean status) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "update Product "
                        + "set  status = ? "
                        + "where productID = ?";
                ps = con.prepareStatement(sql);
                ps.setBoolean(1, status);
                ps.setInt(2, productID);
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

    public int getNumberOfPageForUser() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int size = 0;
        int numofpages = 0;
        try {
            con = DBHelper.makeConnection();
            String sql = "select count(productID) as 'size' "
                    + "from product "
                    + "where status = 1 ";
            if (con != null) {
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    size = rs.getInt("size");
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
            numofpages = (int) Math.ceil(1.0 * size / RECORDS_IN_PAGE);
        }
        return numofpages;
    }

    public int getNumberOfPageForUserSearch(String pName, String categoryName, int priceMin, int priceMax) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int size = 0;
        int numofpages = 0;
        try {
            con = DBHelper.makeConnection();
            String sql = "select count(productID) as 'size' "
                    + "from product "
                    + "where status = 1 and productName like ? "
                    + "and categoryID in "
                    + "(select categoryID from Category where categoryName like ?)"
                    + "and price >= ? and price <= ? ";
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + pName + "%");
                ps.setString(2, "%" + categoryName + "%");
                ps.setInt(3, priceMin);
                ps.setInt(4, priceMax);
                rs = ps.executeQuery();
                if (rs.next()) {
                    size = rs.getInt("size");
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
            numofpages = (int) Math.ceil(1.0 * size / RECORDS_IN_PAGE);
        }
        return numofpages;
    }

    public int getNumberOfPageForAdmin() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int size = 0;
        int numofpages = 0;
        try {
            con = DBHelper.makeConnection();
            String sql = "select count(productID) as 'size' "
                    + "from product ";
            if (con != null) {
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    size = rs.getInt("size");
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
            numofpages = (int) Math.ceil(1.0 * size / RECORDS_IN_PAGE);
        }
        return numofpages;
    }

    public int getNumberOfPageForAdminSearch(String pName, String categoryName, int priceMin, int priceMax) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int size = 0;
        int numofpages = 0;
        try {
            con = DBHelper.makeConnection();
            String sql = "select count(productID) as 'size' "
                    + "from product "
                    + "where status = 1 and productName like ? "
                    + "and categoryID in "
                    + "(select categoryID from Category where categoryName like ?)"
                    + "and price >= ? and price <= ? ";
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + pName + "%");
                ps.setString(2, "%" + categoryName + "%");
                ps.setInt(3, priceMin);
                ps.setInt(4, priceMax);
                rs = ps.executeQuery();
                if (rs.next()) {
                    size = rs.getInt("size");
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
            numofpages = (int) Math.ceil(1.0 * size / RECORDS_IN_PAGE);
        }
        return numofpages;
    }

    public ProductDTO getProductRecommendation(int productID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ProductDTO dto = null;
        try {
            con = DBHelper.makeConnection();
            String sql = "select productID "
                    + "from Product "
                    + "where productID != ? and productId in "
                    + "	(select productID "
                    + "	from BillDetails "
                    + "	where billID = "
                    + "		(select top 1 b.billID "
                    + "		from Bill b, BillDetails bd "
                    + "		where b.numOfProduct > 1 and bd.productID = ? "
                    + "		order by b.BillID desc "
                    + "		) "
                    + "	)";
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setInt(1, productID);
                ps.setInt(2, productID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    int pID = rs.getInt("productID");
                    dto = getProductDTO(pID);
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

    public Map<ProductDTO, String> getShoppingHistory(String userID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Map<ProductDTO, String> list = null;
        try {
            con = DBHelper.makeConnection();
            String sql = "select top(100) percent p.productID,p.productName,p.image,p.price,p.categoryID , b.orderTime "
                    + "from Product p, Bill b, (select bd.productID, r.orderTime "
                    + "	from BillDetails bd, (select billId , orderTime "
                    + "		from Bill "
                    + "		where userID =  ? ) r "
                    + "	where bd.BillID = r.BillID  ) r "
                    + "where p.productID  = r.productID  and r.orderTime = b.orderTime "
                    + "order by b.billID desc";
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, userID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int pID = rs.getInt("productID");
                    String productName = rs.getString("productName");
                    String image = rs.getString("image");
                    float price = rs.getFloat("price");
                    String categoryID = rs.getString("categoryID");
                    CategoryDAO cdao = new CategoryDAO();
                    cdao.getAllCategory();
                    CategoryDTO cdto = cdao.findCategoryDTO(categoryID);
                    String orderTime = rs.getString("orderTime");
                    ProductDTO pdto = new ProductDTO(pID, productName, image, price, cdto);
                    if (list == null) {
                        list = new LinkedHashMap<>();
                    }
                    list.put(pdto, orderTime);
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
        return list;
    }
    public Map<ProductDTO, String> searchShoppingHistory(String userID, String name, String date) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Map<ProductDTO, String> list = null;
        try {
            con = DBHelper.makeConnection();
            String sql = "select top(100) percent p.productID,p.productName,p.image,p.price,p.categoryID , b.orderTime "
                    + "from Product p, Bill b, (select bd.productID, r.orderTime "
                    + "	from BillDetails bd, (select billId , orderTime "
                    + "		from Bill "
                    + "		where userID =  ? and orderTime like ? ) r "
                    + "	where bd.BillID = r.BillID  ) r "
                    + "where p.productID  = r.productID  and r.orderTime = b.orderTime and p.productName like ? "
                    + "order by b.billID desc";
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, userID);
                ps.setString(2, "%"+ date+"%");
                ps.setString(3, "%"+name+"%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    int pID = rs.getInt("productID");
                    String productName = rs.getString("productName");
                    String image = rs.getString("image");
                    float price = rs.getFloat("price");
                    String categoryID = rs.getString("categoryID");
                    CategoryDAO cdao = new CategoryDAO();
                    cdao.getAllCategory();
                    CategoryDTO cdto = cdao.findCategoryDTO(categoryID);
                    String orderTime = rs.getString("orderTime");
                    ProductDTO pdto = new ProductDTO(pID, productName, image, price, cdto);
                    if (list == null) {
                        list = new LinkedHashMap<>();
                    }
                    list.put(pdto, orderTime);
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
        return list;
    }
}
