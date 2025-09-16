package entities;

import java.sql.Date;

public class Food {
	private String foodId;
	private String foodName;
	private float price;
	private String description;
	private String picture;
	private Date createDate;
	private boolean active;
	public Food() {
		
	}
	public Food(String foodId, String foodName, float price, String description, String picture, Date createDate, boolean active) {
		super();
		this.foodId = foodId;
		this.foodName = foodName;
		this.price = price;
		this.description = description;
		this.picture = picture;
		this.createDate = createDate;
		this.active = active;
	}

	public String getFoodId() {
		return foodId;
	}
	public void setFoodId(String foodId) {
		this.foodId = foodId;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
}