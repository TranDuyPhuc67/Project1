package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DatabaseUtil;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Kết nối đến cơ sở dữ liệu
        Connection conn = DatabaseUtil.getConnect();

        // Truy vấn thông tin người dùng từ cơ sở dữ liệu
        String query = "SELECT userid, password FROM Users WHERE username = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("password");

                // Kiểm tra mật khẩu
                if (storedPassword.equals(password)) {
                    // Nếu mật khẩu đúng, lấy userid và lưu vào session
                    String userId = rs.getString("userid");
                    HttpSession session = request.getSession(true);
                    session.setAttribute("userId", userId);
                    session.setAttribute("username", username); // Lưu username vào session


                    // Thiết lập session tồn tại 30 phút
                    session.setMaxInactiveInterval(30*60); // 30 phút

                    // Redirect đến trang giỏ hàng hoặc trang chủ
                    response.sendRedirect("index.jsp");
                } else {
                    // Mật khẩu sai
                    request.setAttribute("msg", "Sai mật khẩu");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            } else {
                // Tên đăng nhập không tồn tại
                request.setAttribute("msg", "Tên đăng nhập không tồn tại");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("msg", "Đã có lỗi xảy ra");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

