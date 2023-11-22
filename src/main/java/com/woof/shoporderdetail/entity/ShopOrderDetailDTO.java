package com.woof.shoporderdetail.entity;

import java.io.Serializable;
import java.util.Objects;

public class ShopOrderDetailDTO implements Serializable {


    private Integer prodNo;

    private String prodName;

    private Integer prodPrice;

    private Integer orderAmount;


    public Integer getProdNo() {
        return prodNo;
    }

    public void setProdNo(Integer prodNo) {
        this.prodNo = prodNo;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public Integer getProdPrice() {
        return prodPrice;
    }

    public void setProdPrice(Integer prodPrice) {
        this.prodPrice = prodPrice;
    }

    public Integer getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Integer orderAmount) {
        this.orderAmount = orderAmount;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShopOrderDetailDTO that = (ShopOrderDetailDTO) o;
        return Objects.equals(prodNo, that.prodNo) && Objects.equals(prodName, that.prodName) && Objects.equals(prodPrice, that.prodPrice) && Objects.equals(orderAmount, that.orderAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prodNo, prodName, prodPrice, orderAmount);
    }


    @Override
    public String toString() {
        return "ShopOrderDetailDTO{" +
                "prodNo=" + prodNo +
                ", prodName='" + prodName + '\'' +
                ", prodPrice=" + prodPrice +
                ", orderAmount=" + orderAmount +
                '}';
    }
}
