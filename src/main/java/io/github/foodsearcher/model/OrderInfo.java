package io.github.foodsearcher.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
	private Long iD;
	private Long userID;
	private Long storeID;
	private Long addressID;
	private int numGoods;
	private static String date;
//	private GoodInfo goodInfo;
	@OneToMany
	private Set<GoodInfo> Goods;
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
	
	public static void setDate() {
		date = new Date().toString();
	}
	public String getDate() {
		return date;
	}
	
	public void setGoodsInfo(Set<GoodInfo> goods) {
		Goods = goods;
	}
	
	
	public Set<GoodInfo> getGoodsInfo() {
		return Goods;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getState() {
		return state;
	}
}
