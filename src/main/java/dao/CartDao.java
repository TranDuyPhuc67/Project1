package dao;

import java.util.List;

import entities.CartItem;

public interface CartDao {
    boolean addToCart(CartItem cartItem); // Thêm sản phẩm vào giỏ hàng
    List<CartItem> getCartByUserId(String userId); // Lấy danh sách sản phẩm trong giỏ hàng
    boolean updateCartQuantity(String cartId, String userId, int quantity); // Cập nhật số lượng
    boolean removeFromCart(String cartId, String userId); // Xóa sản phẩm khỏi giỏ hàng
}
