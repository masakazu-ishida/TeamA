package jp.co.brmy.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.brmy.service.CartAddService;

/**
 * Servlet implementation class CartAddController
 */
@WebServlet("/CartAddController")
public class CartAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CartAddController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		HttpSession session = request.getSession();

		int itemId = Integer.parseInt(request.getParameter("userId"));
		int amount = Integer.parseInt(request.getParameter("amount"));

		request.setAttribute("itemId", itemId);
		request.setAttribute("amount", amount);

		if (session.getAttribute("user").toString() == null) {
			String pass = "/WEB-INF/jsp/login.jsp";
			request.setAttribute("source", "2");
			RequestDispatcher rd = request.getRequestDispatcher(pass);
			rd.forward(request, response);

			return;
		}
		String userId = session.getAttribute("user").toString();

		try {
			CartAddService service = new CartAddService();
			service.cartadd(itemId, amount, userId);//後でsessionから取得したユーザー名に変更
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		String pass = "/CartDisplayController";
		RequestDispatcher rd = request.getRequestDispatcher(pass);
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
