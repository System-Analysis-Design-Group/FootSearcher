package io.github.foodsearcher.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class DishInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private Long storeId;
	private String typeName;
	private String description;
	private double orPrice;
	private double cuPrice;
	private String imagePath;
	
	public void setId(Long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}
	public void setTypeName(String name) {
		this.typeName = name;
	}
	public void setDescription(String desc) {
		this.description = desc;
	}
	public void setOrPrice(double orPrice) {
		this.orPrice = orPrice;
	}
	public void setCuPrice(double cuPrice) {
		this.cuPrice = cuPrice;
	}
	public void setImagePath(String path) {
		this.imagePath= path;
	}
	
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public Long getStoreId() {
		return storeId;
	}
	public String getTypeId() {
		return typeName;
	}
	public String getDescription() {
		return description;
	}
	public double getOrPrice() {
		return orPrice;
	}
	public double getCuPrice() {
		return cuPrice;
	}
	public String getImagePath() {
		return imagePath;
	}
}
