package com.woof.shoporder.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.*;

import com.google.gson.annotations.Expose;
import com.woof.member.entity.Member;

@Entity
@Table(name="shop_order")
public class ShopOrder implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Expose
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="SHOP_ORDER_NO", updatable = false)		//商城訂單編號
	private Integer shopOrderNo;


	@Expose
    @ManyToOne
    @JoinColumn(name = "MEM_NO" , referencedColumnName = "MEM_NO")
	private Member member;

	@Expose
    @Column(name="PROD_ORDER_DATE", nullable=false)		//訂單成立時間
	private Timestamp prodOrderDate;

	@Expose
    @Column(name="PAY_METHOD", nullable=false, columnDefinition = "TINYINT")			//付款方式 0:信用卡 1:匯款 
	private Integer payMethod;

	@Expose
    @Column(name="SHIP_METHOD", nullable=false)			//取貨方式 0:宅配 1:超商取貨(未支援)
	private Boolean shipMethod;

	@Expose
    @Column(name="ORDER_STATUS", nullable=false, columnDefinition = "TINYINT")		//訂單狀態 0:成立 1:出貨 2:完成 3:取消 4:未付款
	private Integer orderStatus;

	@Expose
    @Column(name="REC_NAME", nullable=false)			//收件人姓名
	private String recName;

	@Expose
    @Column(name="REC_MOBILE", nullable=false)			//收件人電話
	private String recMobile;

	@Expose
    @Column(name="REC_ADDRESS", nullable=false)			//收件人地址
	private String recAddress;

	@Expose
    @Column(name="HAS_RETURN", nullable=false)			//是否有退貨 0:無退貨, 1:有退貨
	private Boolean hasReturn;

	@Expose
    @Column(name="MO_COIN", nullable=false)				//折抵毛毛幣 Default:0 不使用就是0
	private Integer moCoin;

	@Expose
    @Column(name="ORDER_TOTAL_PRICE", nullable=false)			//訂單總金額
	private Integer orderTotalPrice;

	@Expose
    @Column(name="ACTUAL_PRICE", nullable=false)		//實付金額 含扣除毛毛幣
	private Integer actualprice;

	public ShopOrder() {
	}

	public Integer getShopOrderNo() {
		return shopOrderNo;
	}

	public void setShopOrderNo(Integer shopOrderNo) {
		this.shopOrderNo = shopOrderNo;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
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

	public Integer getOrderTotalPrice() {
		return orderTotalPrice;
	}

	public void setOrderTotalPrice(Integer orderTotalPrice) {
		this.orderTotalPrice = orderTotalPrice;
	}

	public Integer getActualprice() {
		return actualprice;
	}

	public void setActualprice(Integer actualprice) {
		this.actualprice = actualprice;
	}
	
	
	
}


