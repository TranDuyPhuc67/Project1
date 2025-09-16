package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DatabaseUtil;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        try {
        	Connection con = DatabaseUtil.getConnect();
         
            // Kiểm tra xem tên đăng nhập đã tồn tại chưa
            String checkQuery = "SELECT * FROM Users WHERE username = ?";
            PreparedStatement checkStmt = con.prepareStatement(checkQuery);
            checkStmt.setString(1, username);
            boolean userExists = checkStmt.executeQuery().next();

            if (userExists) {
                request.setAttribute("msg", "Tên đăng nhập đã tồn tại.");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            } else {
                // Thêm người dùng mới vào cơ sở dữ liệu
                String query = "INSERT INTO Users (username, password, email) VALUES (?, ?, ?)";
                PreparedStatement stmt = con.prepareStatement(query);
                stmt.setString(1, username);
                stmt.setString(2, password);
                stmt.setString(3, email);
                stmt.executeUpdate();

                // Chuyển hướng sau khi đăng ký thành công
                response.sendRedirect("FoodServlet");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "Đăng ký thất bại, vui lòng thử lại.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}
