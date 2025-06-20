package jp.co.brmy.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.brmy.dto.AdministratorsDTO;
import jp.co.brmy.service.AdminLoginService;

/**
 * Servlet implementation class AdminLoguinController
 */
@WebServlet(name = "AdminLoginController", urlPatterns = { "/AdminLoginController" })
public class AdminLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminLoginController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/adminlogin.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path1 = "/WEB-INF/jsp/adminmain.jsp";
		String path2 = "/WEB-INF/jsp/adminlogin.jsp";
		String id = request.getParameter("userid");
		String password = request.getParameter("password");

		AdminLoginService ALS = new AdminLoginService();
		AdministratorsDTO dto = null;
		try {
			dto = ALS.findById(id, password);

			if (dto != null) {
				RequestDispatcher rd1 = request.getRequestDispatcher(path1);
				rd1.forward(request, response);
			} else {
				request.setAttribute("errorMsg", "ユーザー名またはパスワードが間違っています。");
				RequestDispatcher rd2 = request.getRequestDispatcher(path2);
				rd2.forward(request, response);
			}

		} catch (SQLException | ServletException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}

}
