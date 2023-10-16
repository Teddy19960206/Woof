package com.woof.shoporder.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;
import javax.persistence.*;

@Entity
@Table(name="shop_order")
public class ShopOrder implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="SHOP_ORDER_NO", updatable = false)		//商城訂單編號
	private Integer shopOrderNo;

    @Column(name="MEM_NO", nullable=false)				//會員編號
	private Integer memNo;

    @Column(name="PROD_ORDER_DATE", nullable=false)		//訂單成立時間
	private Timestamp prodOrderDate;

    @Column(name="PAY_METHOD", nullable=false, columnDefinition = "TINYINT")			//付款方式 0:信用卡 1:匯款 
	private Integer payMethod;

    @Column(name="SHIP_METHOD", nullable=false)			//取貨方式 0:宅配 1:超商取貨(未支援)
	private Boolean shipMethod;

    @Column(name="ORDER_STATUS", nullable=false, columnDefinition = "TINYINT")		//訂單狀態 0:成立 1:出貨 2:完成 3:取消 4:未付款
	private Integer orderStatus;

    @Column(name="REC_NAME", nullable=false)			//收件人姓名
	private String recName;

    @Column(name="REC_MOBILE", nullable=false)			//收件人電話
	private String recMobile;

    @Column(name="REC_ADDRESS", nullable=false)			//收件人地址
	private String recAddress;

    @Column(name="HAS_RETURN", nullable=false)			//是否有退貨 0:無退貨, 1:有退貨
	private Boolean hasReturn;

    @Column(name="MO_COIN", nullable=false)				//折抵毛毛幣 Default:0 不使用就是0
	private Integer moCoin;

    @Column(name="PROD_TOTAL", nullable=false)			//訂單總金額
	private Integer prodTotal;

    @Column(name="ACTUAL_AMOUNT", nullable=false)		//實付金額 含扣除毛毛幣
	private Integer actualAmount;

	public ShopOrder() {
	}

	public Integer getShopOrderNo() {
		return shopOrderNo;
	}

	public void setShopOrderNo(Integer shopOrderNo) {
		this.shopOrderNo = shopOrderNo;
	}

	public Integer getMemNo() {
		return memNo;
	}

	public void setMemNo(Integer memNo) {
		this.memNo = memNo;
	}

	public Timestamp getProdOrderDate() {
		return prodOrderDate;
	}

	public void setProdOrderDate(Timestamp prodOrderDate) {
		this.prodOrderDate = prodOrderDate;
	}

	public Integer getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(Integer payMethod) {
		this.payMethod = payMethod;
	}

	public Boolean getShipMethod() {
		return shipMethod;
	}

	public void setShipMethod(Boolean shipMethod) {
		this.shipMethod = shipMethod;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getRecName() {
		return recName;
	}

	public void setRecName(String recName) {
		this.recName = recName;
	}

	public String getRecMobile() {
		return recMobile;
	}

	public void setRecMobile(String recMobile) {
		this.recMobile = recMobile;
	}

	public String getRecAddress() {
		return recAddress;
	}

	public void setRecAddress(String recAddress) {
		this.recAddress = recAddress;
	}

	public Boolean getHasReturn() {
		return hasReturn;
	}

	public void setHasReturn(Boolean hasReturn) {
		this.hasReturn = hasReturn;
	}

	public Integer getMoCoin() {
		return moCoin;
	}

	public void setMoCoin(Integer moCoin) {
		this.moCoin = moCoin;
	}

	public Integer getProdTotal() {
		return prodTotal;
	}

	public void setProdTotal(Integer prodTotal) {
		this.prodTotal = prodTotal;
	}

	public Integer getActualAmount() {
		return actualAmount;
	}

	public void setActualAmount(Integer actualAmount) {
		this.actualAmount = actualAmount;
	}

	@Override
	public int hashCode() {
		return Objects.hash(actualAmount, hasReturn, memNo, moCoin, orderStatus, payMethod, prodOrderDate, prodTotal,
				recAddress, recMobile, recName, shipMethod, shopOrderNo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShopOrder other = (ShopOrder) obj;
		return Objects.equals(actualAmount, other.actualAmount) && Objects.equals(hasReturn, other.hasReturn)
				&& Objects.equals(memNo, other.memNo) && Objects.equals(moCoin, other.moCoin)
				&& Objects.equals(orderStatus, other.orderStatus) && Objects.equals(payMethod, other.payMethod)
				&& Objects.equals(prodOrderDate, other.prodOrderDate) && Objects.equals(prodTotal, other.prodTotal)
				&& Objects.equals(recAddress, other.recAddress) && Objects.equals(recMobile, other.recMobile)
				&& Objects.equals(recName, other.recName) && Objects.equals(shipMethod, other.shipMethod)
				&& Objects.equals(shopOrderNo, other.shopOrderNo);
	}

	@Override
	public String toString() {
		return "ShopOrderVO [shopOrderNo=" + shopOrderNo + ", memNo=" + memNo + ", prodOrderDate=" + prodOrderDate
				+ ", payMethod=" + payMethod + ", shipMethod=" + shipMethod + ", orderStatus=" + orderStatus
				+ ", recName=" + recName + ", recMobile=" + recMobile + ", recAddress=" + recAddress + ", hasReturn="
				+ hasReturn + ", moCoin=" + moCoin + ", prodTotal=" + prodTotal + ", actualAmount=" + actualAmount
				+ "]";
	}
}
