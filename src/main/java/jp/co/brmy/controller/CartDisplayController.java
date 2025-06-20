package jp.co.brmy.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.brmy.dto.ItemsInCartDTO;
import jp.co.brmy.service.CartDisplayService;

/**
 * Servlet implementation class CartDisplayController
 */
@WebServlet("/CartDisplayController")
public class CartDisplayController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CartDisplayController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//		response.getWriter().append("Served at: ").append(request.getContextPath());

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		HttpSession session = request.getSession();

		if (session.getAttribute("user").toString() == null) {
			String pass = "/WEB-INF/jsp/login.jsp";
			request.setAttribute("source", "1");
			RequestDispatcher rd = request.getRequestDispatcher(pass);
			rd.forward(request, response);

			return;
		}
		String userId = session.getAttribute("user").toString();
		CartDisplayService service = new CartDisplayService();
		List<ItemsInCartDTO> list = new ArrayList<>();
		try {
			list = service.cartItems(userId);
			//
			//			list = service.cartItems("user");//後でsessionから取得したユーザー名に変更
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		if (list.size() == 0) {
			request.setAttribute("cart", null);
		} else {
			request.setAttribute("cart", list);

		}

		String pass = "/WEB-INF/jsp/cart.jsp";
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
