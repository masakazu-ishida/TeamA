package jp.co.brmy.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import jp.co.ecsite.dto.UsersDTO;
//import jp.co.ecsite.service.AuthenticatinService;

/**
 * Servlet implementation class LoginController
 泣きのもう一回
 サーバで変えた
 サーバで更に変えた！うまくプルされるかな？
 
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, responseex);
		request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("userid");
		String password = request.getParameter("password");

//		AuthenticatinService service = new AuthenticatinService();
//		try {
//			UsersDTO dto = service.execute(id, password);
//			request.getRequestDispatcher("/WEB-INF/result.jsp").forward(request, response);
//
//		} catch (SQLException e) {
//			// TODO 自動生成された catch ブロック
//			e.printStackTrace();
//		}

	}

}
