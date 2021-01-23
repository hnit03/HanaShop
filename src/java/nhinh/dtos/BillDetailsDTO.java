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
public class BillDetailsDTO implements Serializable{
    
    private String bdID;
    private String billID;
    private String productID;
    private int quantity;

    public BillDetailsDTO() {
    }

    public BillDetailsDTO(String bdID, String billID, String productID, int quantity) {
        this.bdID = bdID;
        this.billID = billID;
        this.productID = productID;
        this.quantity = quantity;
    }

    /**
     * @return the bdID
     */
    public String getBdID() {
        return bdID;
    }

    /**
     * @param bdID the bdID to set
     */
    public void setBdID(String bdID) {
        this.bdID = bdID;
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
     * @return the productID
     */
    public String getProductID() {
        return productID;
    }

    /**
     * @param productID the productID to set
     */
    public void setProductID(String productID) {
        this.productID = productID;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    
    
}
