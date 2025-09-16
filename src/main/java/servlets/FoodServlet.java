package servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FoodDao;
import dao.FoodImpl;
import entities.Food;


@WebServlet("/FoodServlet")
public class FoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FoodServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF8");
		FoodDao foodDao=new FoodImpl();
		String action=request.getParameter("action");
		if(action==null) {
			request.setAttribute("foods", foodDao.getAll());
			request.getRequestDispatcher("index.jsp?page=foodhome").forward(request, response);
		}else if(action.equals("foods"))
		{
			request.setAttribute("foods", foodDao.getAll());
			request.getRequestDispatcher("index.jsp?page=foodshop").forward(request, response);
		}else if(action.equals("admin")){
			request.setAttribute("foods", foodDao.getAll());
			request.getRequestDispatcher("index.jsp?page=foodadmin").forward(request, response);
		}
		else if(action.equals("detail") || action.equals("edit")) {
			String foodid=request.getParameter("foodid");
			request.setAttribute("food", foodDao.getById(foodid));
			if(action.equals("detail"))
				request.getRequestDispatcher("index.jsp?page=fooddetails").forward(request, response);
			else
				request.getRequestDispatcher("index.jsp?page=edit").forward(request, response);
		}else if(action.equals("delete"))
		{
			String foodid=request.getParameter("foodid");
			if(foodDao.delete(foodid))
				request.setAttribute("msg", "Xóa thành công");
			else
				request.setAttribute("msg", "Xóa không thành công");
			request.setAttribute("foods", foodDao.getAll());
			request.getRequestDispatcher("index.jsp?page=foodadmin").forward(request, response);
		}else if(action.equals("add"))
		{
			request.setAttribute("foods", foodDao.getAll());
			request.getRequestDispatcher("index.jsp?page=add").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}