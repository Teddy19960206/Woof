package com.woof.shoporderdetail.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;
import javax.persistence.*;

import com.google.gson.annotations.Expose;

//可能要重做

@Entity
@Table(name = "shop_order_detail")
public class ShopOrderDetail implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Expose
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="SHOP_ORDER_DETAIL_NO", updatable = false)		//商城訂單明細編號
	private Integer shopOrderDetailNo;
	
	@Expose
    @Column(name="SHOP_ORDER_NO", nullable=false)   //商城訂單編號
	private Integer shopOrderNo;
	
	
	
	
	
	
	
	
	

}

