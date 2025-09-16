package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DatabaseUtil {
	private static String username="sa";
	private static String password="123456789";
	private static String url="jdbc:sqlserver://localhost:1433;databaseName=Project1;Encrypt=True;TrustServerCertificate=True";
	public static Connection getConnect() {
		Connection con=null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con=DriverManager.getConnection(url,username, password);
			System.out.println("Kết nối thành công");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
}