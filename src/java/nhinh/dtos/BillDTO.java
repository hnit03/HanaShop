/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhinh.dtos;

import java.io.Serializable;

/**
 *
 * @author PC
 */
public class BillDTO implements Serializable{

    private String billID;
    private String userID;
    private float totalPrice;
    private int numOfProduct;
    private String orderTime;
    
    public BillDTO() {
    }

    public BillDTO(String billID, String userID, float totalPrice, int numOfProduct, String orderTime) {
        this.billID = billID;
        this.userID = userID;
        this.totalPrice = totalPrice;
        this.numOfProduct = numOfProduct;
        this.orderTime = orderTime;
    }

    /**
     * @return the billID
     */
    public String getBillID() {
        return billID;
    }

    /**
     * @param billID the billID to set
     */
    public void setBillID(String billID) {
        this.billID = billID;
    }

    /**
     * @return the userID
     */
    public String getUserID() {
        return userID;
    }

    /**
     * @param userID the userID to set
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * @return the totalPrice
     */
    public float getTotalPrice() {
        return totalPrice;
    }

    /**
     * @param totalPrice the totalPrice to set
     */
    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * @return the numOfProduct
     */
    public int getNumOfProduct() {
        return numOfProduct;
    }

    /**
     * @param numOfProduct the numOfProduct to set
     */
    public void setNumOfProduct(int numOfProduct) {
        this.numOfProduct = numOfProduct;
    }

    /**
     * @return the orderTime
     */
    public String getOrderTime() {
        return orderTime;
    }

    /**
     * @param orderTime the orderTime to set
     */
    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    
    
}
