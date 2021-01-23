/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhinh.cart;

import java.util.HashMap;
import java.util.Map;
import nhinh.dtos.ProductDTO;

/**
 *
 * @author PC
 */
public class CartObject {

    private Map<ProductDTO, Integer> products;

    public Map<ProductDTO, Integer> getProducts() {
        return products;
    }

    public void addProductToCart(ProductDTO dto, int amount) {
        if (this.products == null) {
            this.products = new HashMap<>();
        }
        boolean isExist = false;
        for (ProductDTO pDTO : products.keySet()) {
            if (dto.getProductID().equals(pDTO.getProductID())) {
                int quantity = this.products.get(pDTO);
                quantity = this.products.get(pDTO) + amount;
                this.products.replace(pDTO, quantity);
                isExist = true;
                break;
            }
        }
        if (!isExist) {
            this.products.put(dto, amount);
        }
    }

    public void increaseProductToCart(String productID) {
        if (this.products == null) {
            return;
        }
        for (ProductDTO pDTO : products.keySet()) {
            if (pDTO.getProductID().equals( productID)) {
                int quantity = this.products.get(pDTO);
                if (quantity < pDTO.getQuantity()) {
                    quantity = this.products.get(pDTO) + 1;
                    this.products.replace(pDTO, quantity);
                    break;
                }
            }
        }
    }

    public void minusProductFromCart(String productID) {
        if (this.products == null) {
            return;
        }
        for (ProductDTO pDTO : products.keySet()) {
            if (pDTO.getProductID().equals(productID)) {
                int quantity = this.products.get(pDTO);
                if (quantity > 1) {
                    quantity = this.products.get(pDTO) - 1;
                    this.products.replace(pDTO, quantity);
                    break;
                }
            }
        }
    }

    public void removeProductFromCart(String productID) {
        if (this.products == null) {
            return;
        }
        for (ProductDTO pdto : products.keySet()) {
            if (pdto.getProductID().equals(productID)) {
                this.products.remove(pdto);
                if (this.products.isEmpty()) {
                    this.products = null;
                }
                break;
            }
        }
    }
}
