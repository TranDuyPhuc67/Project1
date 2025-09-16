package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import entities.CartItem;

public class CartDaoImpl implements CartDao {
    @Override
    public boolean addToCart(CartItem cartItem) {
        String sql = "INSERT INTO Cart (cartid, userid, foodid, quantity, status) VALUES (?, ?, ?, ?, 'active')";
        try (Connection conn = DatabaseUtil.getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cartItem.getFoodId());
            stmt.setString(2, cartItem.getFoodName());
            stmt.setInt(3, (int) cartItem.getPrice());
            stmt.setInt(4, cartItem.getQuantity());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<CartItem> getCartByUserId(String userId) {
        String sql = """
            SELECT c.cartid, f.foodName, f.price, c.quantity, (f.price * c.quantity) AS total
            FROM Cart c
            JOIN Foods f ON c.foodid = f.foodid
            WHERE c.userid = ? AND c.status = 'active'
        """;
        List<CartItem> cartItems = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                CartItem item = new CartItem(
                    rs.getString("foodId"),
                    rs.getString("foodName"),
                    rs.getDouble("price"),
                    rs.getInt("quantity")

                );
                cartItems.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cartItems;
    }

    @Override
    public boolean updateCartQuantity(String cartId, String userId, int quantity) {
        String sql = "UPDATE Cart SET quantity = ? WHERE cartid = ? AND userid = ?";
        try (Connection conn = DatabaseUtil.getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, quantity);
            stmt.setString(2, cartId);
            stmt.setString(3, userId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean removeFromCart(String cartId, String userId) {
        String sql = "DELETE FROM Cart WHERE cartid = ? AND userid = ?";
        try (Connection conn = DatabaseUtil.getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cartId);
            stmt.setString(2, userId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
