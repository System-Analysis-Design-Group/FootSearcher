package io.github.foodsearcher.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.ws.rs.DefaultValue;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

// import java.util.List;
@Entity
public class OrderInfo implements Serializable {

    private static final long serialVersionUID = 2L;
	@Id
    @Column(unique=true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long iD;
	private Long userID;
	private Long storeID;
	private Long addressID;
	private int numGoods;
	private String date;
//	private GoodInfo goodInfo;
	@OneToMany
	private List<GoodInfo> goodsInfo;
//	private List<Integer> dishNum;
//	private List<Long> dishId;
	private int state;
	
	public void setID(Long id) {
		this.iD = id;
	}
	public Long getID() {
		return iD;
	}
	public void setUserID(Long userID) {
		this.userID = userID;
	}
	public Long getUserID() {
		return userID;
	}
	public void setStoreID(Long storeID) {
		this.storeID = storeID;
	}
	public Long getStoreID() {
		return storeID;
	}
	public void setAddressID(Long addressID) {
		this.addressID = addressID;
	}
	public Long getAddressID() {
		return addressID;
	}
	public void setNumGoods(int numGoods) { this.numGoods = numGoods; }
	public int getNumGoods() {
		return numGoods;
	}
//	public List<Integer> getDishNum() {
//		return dishNum;
//	}
//	public void setDishNum(List<Integer> dishNum) {
//		this.dishNum = dishNum;
//	}
//	public List<Long> getDishId() {
//		return dishId;
//	}
//	public void setDishId(List<Long> dishId) {
//		this.dishId = dishId;
//	}
	public void setDate() {
		date = new Date().toString();
	}
	public String getDate() {
		return date;
	}
	
	public void setGoodsInfo(List<GoodInfo> goods) {
		goodsInfo = goods;
	}
	
	
	public List<GoodInfo> getGoodsInfo() {
		return goodsInfo;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getState() {
		return state;
	}
}
