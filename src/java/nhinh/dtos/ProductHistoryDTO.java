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
public class ProductHistoryDTO implements Serializable {

    private String historyID;
    private ProductDTO productDTO;
    private String userID;
    private String action;
    private String date;

    public ProductHistoryDTO() {
    }

    public ProductHistoryDTO(String historyID, ProductDTO productDTO, String userID, String action, String date) {
        this.historyID = historyID;
        this.productDTO = productDTO;
        this.userID = userID;
        this.action = action;
        this.date = date;
    }

    /**
     * @return the historyID
     */
    public String getHistoryID() {
        return historyID;
    }

    /**
     * @param historyID the historyID to set
     */
    public void setHistoryID(String historyID) {
        this.historyID = historyID;
    }

    /**
     * @return the productdto
     */
    public ProductDTO getProductDTO() {
        return productDTO;
    }

    /**
     * @param productdto the productdto to set
     */
    public void setProductDTO(ProductDTO productDTO) {
        this.productDTO = productDTO;
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
     * @return the action
     */
    public String getAction() {
        return action;
    }

    /**
     * @param action the action to set
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    
}
