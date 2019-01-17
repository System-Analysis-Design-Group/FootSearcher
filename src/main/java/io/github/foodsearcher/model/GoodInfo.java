package io.github.foodsearcher.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class GoodInfo implements Serializable{
	private static final long serialVersionUID = 3L;
	
	@Id
	@GeneratedValue
	private Long id;
	private int num;
	private Long dishId;
	private double unitPrice;
	// private OrderInfo orderInfo;
	
//	@ManyToOne(fetch=FetchType.LAZY)
//	public OrderInfo getOrderInfo() {
//		return orderInfo;
//	}
//	
//	public void setOrderInfo(OrderInfo orderInfo) {
//		this.orderInfo = orderInfo;
//	}
	
	curl
	public Long getId() {
		return id;
	}
	
	public void setId(Long goodID) {
		id = goodID;
	}
	
	public int getNum() {
		return num;
	}
	
	public void setNum(int num) {
		this.num = num;
	}
	
	public double getUnitPrice() {
		return unitPrice;
	}
	
	public void setUnitPrice(double price) {
		unitPrice = price;
	}
	
	public Long getDishId() {
		return dishId;
	}
	public void setDishId(Long dishId) {
		this.dishId = dishId;
	}
}
