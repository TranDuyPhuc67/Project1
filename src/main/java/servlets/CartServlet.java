package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DatabaseUtil;
import dao.FoodDao;
import dao.FoodImpl;
import dao.CartDao;
import dao.CartDaoImpl;
import entities.CartItem;
import entities.Food;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        
        String userId = (String) session.getAttribute("userid"); // Lấy userId từ session
        CartDao cartDao = new CartDaoImpl();
        if ("addToCart".equals(action)) {
            String foodId = request.getParameter("foodId");
            if (foodId != null) {
                // Lấy thông tin sản phẩm
                FoodDao foodDao = new FoodImpl();
                Food food = foodDao.getById(foodId);

                if (food != null) {
                    // Tạo CartItem và thêm vào giỏ hàng
                    CartItem cartItem = new CartItem(food.getFoodId(), food.getFoodName(), food.getPrice(),1);
                    cartDao.addToCart(cartItem);

                    // Gửi thông báo
                    request.setAttribute("msg", "Sản phẩm đã được thêm vào giỏ hàng!");
                } else {
                    request.setAttribute("msg", "Sản phẩm không tồn tại!");
                }
            }
            // Điều hướng về trang foodshop.jsp
            FoodDao foodDao = new FoodImpl();
            request.setAttribute("foods", foodDao.getAll());
            request.getRequestDispatcher("index.jsp?page=foodshop").forward(request, response);
        } else if ("viewCart".equals(action)) {

            // Hiển thị giỏ hàng
            List<CartItem> cartItems = cartDao.getCartByUserId(userId);
            request.setAttribute("cartItems", cartItems);
            request.getRequestDispatcher("index.jsp?page=cart").forward(request, response);
        }
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doGet(request, response);
    }

    private void displayCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userid");

        try (Connection conn = DatabaseUtil.getConnect()) {
            String sql = "SELECT c.cartid, f.foodName, f.price, c.quantity, (f.price * c.quantity) AS total " +
                         "FROM Cart c JOIN Foods f ON c.foodid = f.foodid " +
                         "WHERE c.userid = ? AND c.status = 'active'";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, userId);

            ResultSet rs = stmt.executeQuery();
            List<CartItem> cartItems = new ArrayList<>();

            while (rs.next()) {
                CartItem item = new CartItem(
                    rs.getString("foodId"),
                    rs.getString("foodName"),
                    rs.getDouble("price"),
                    rs.getInt("quantity")
                );
                cartItems.add(item);
            }

            request.setAttribute("cartItems", cartItems);
            RequestDispatcher dispatcher = request.getRequestDispatcher("cart.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Lỗi: " + e.getMessage());
        }
    }

    private void addToCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String cartId = request.getParameter("cartid");
        String userId = request.getParameter("userid");
        String foodId = request.getParameter("foodid");
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        try (Connection conn = DatabaseUtil.getConnect()) {
            // Cập nhật câu lệnh SQL theo thứ tự adddate trước status
            String sql = "INSERT INTO Cart (cartid, userid, foodid, quantity, adddate, status) VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP, 'active')";
            PreparedStatement stmt = conn.prepareStatement(sql);

            // Đảm bảo các tham số đúng với cột trong bảng
            stmt.setString(1, cartId);  // cartid
            stmt.setString(2, userId);  // userid
            stmt.setString(3, foodId);  // foodid
            stmt.setInt(4, quantity);   // quantity

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                // Redirect đến trang giỏ hàng nếu thêm thành công
                response.sendRedirect("cart.jsp");
            } else {
                // In thông báo nếu thêm thất bại
                response.getWriter().println("Thêm sản phẩm vào giỏ thất bại.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Lỗi: " + e.getMessage());
        }
    }


    private void updateCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String cartId = request.getParameter("cartid");
        String userId = request.getParameter("userid");
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        try (Connection conn = DatabaseUtil.getConnect()) {
            String sql = "UPDATE Cart SET quantity = ? WHERE cartid = ? AND userid = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, quantity);
            stmt.setString(2, cartId);
            stmt.setString(3, userId);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                response.sendRedirect("cart.jsp");
            } else {
                response.getWriter().println("Cập nhật số lượng thất bại.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Lỗi: " + e.getMessage());
        }
    }

    private void deleteFromCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String cartId = request.getParameter("cartid");
        String userId = request.getParameter("userid");

        try (Connection conn = DatabaseUtil.getConnect()) {
            String sql = "DELETE FROM Cart WHERE cartid = ? AND userid = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, cartId);
            stmt.setString(2, userId);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                response.sendRedirect("cart.jsp");
            } else {
                response.getWriter().println("Xóa sản phẩm thất bại.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Lỗi: " + e.getMessage());
        }
    }

}
