package dao;

import java.util.List;

import entities.Food;

public interface FoodDao {
	public List<Food> getAll();
	public Food getById(String foodid);
	public boolean insert(Food f);
	public boolean update(Food f);
	public boolean delete(String foodid);

}