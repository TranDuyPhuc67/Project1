package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.internal.compiler.batch.Main;

import entities.Food;

public class FoodImpl implements FoodDao {
	private Connection con = null;
	public FoodImpl() {
        this.con = DatabaseUtil.getConnect();
    }
	@Override
	public List<Food> getAll()  {
		List<Food> data=new ArrayList<>();
		try {
			Statement st =  con.createStatement();
			ResultSet rs = st.executeQuery("select * from Foods");
			while (rs.next()) {
				Food f=new Food(rs.getString("foodid"), rs.getString("foodname"), rs.getFloat("price"), rs.getString("description"), rs.getString("picture"), rs.getDate("createdate"), rs.getBoolean("active"));
				data.add(f);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data;
	}

	@Override
	public Food getById(String foodid) {
		Food food=null;
		try {
			PreparedStatement pst = con.prepareStatement("select * from Foods where foodid=?");
			pst.setString(1, foodid);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				food=new Food(rs.getString("foodid"), rs.getString("foodname"), rs.getFloat("price"), rs.getString("description"), rs.getString("picture"), rs.getDate("createdate"), rs.getBoolean("active"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return food;
	}
	
	@Override        
    public boolean insert(Food f) {
	try {
		PreparedStatement pst = con.prepareStatement("insert into foods values(?,?,?,?,?,?,?)");
		pst.setString(1, f.getFoodId());
		pst.setString(2, f.getFoodName());
		pst.setFloat(4, f.getPrice());
		pst.setString(7, f.getDescription());
		pst.setString(8, f.getPicture());
		pst.setDate(9, f.getCreateDate());
		pst.setBoolean(10, f.isActive());
		return pst.executeUpdate()>0;
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return false;
}

@Override
	public boolean update(Food f) {
	try {
		PreparedStatement pst = con.prepareStatement("update foods set foodname=?,price=?,description=?,picture=?,createdate=?,active=? where foodid=?");
		pst.setString(10, f.getFoodId());
		pst.setString(1, f.getFoodName());
		pst.setFloat(3, f.getPrice());
		pst.setString(6, f.getDescription());
		pst.setString(7, f.getPicture());
		pst.setDate(8, f.getCreateDate());
		pst.setBoolean(9, f.isActive());
		return pst.executeUpdate()>0;
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return false;
}

@Override
public boolean delete(String foodid) {
	try {
		PreparedStatement pst = con.prepareStatement("delete from foods where foodid=?");
		pst.setString(1, foodid);
		return pst.executeUpdate()>0;
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return false;
}
	
    public static void main(String[] args) {

        
    }
}