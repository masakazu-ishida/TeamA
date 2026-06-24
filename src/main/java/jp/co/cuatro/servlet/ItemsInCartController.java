package jp.co.cuatro.servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.cuatro.dto.CartDTO;
import jp.co.cuatro.dto.UsersDTO;
import jp.co.cuatro.service.CartAllService;

/**
 * Servlet implementation class ItemsInCartController
 */
@WebServlet("/ItemsInCartController")
public class ItemsInCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ItemsInCartController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		//現在アクセスしているユーザー専用の「箱（セッション）」を用意
		HttpSession session = request.getSession();
		UsersDTO loginUser = (UsersDTO) session.getAttribute("loginUser");
		//箱の中から、loginUser という名前のデータを取り出す
		if (loginUser == null) {
			request.setAttribute("src", "/ItemsInCartController");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/login.jsp");
			dispatcher.forward(request, response);

		} else {
			//			ConfirmationService userService = new ConfirmationService();
			//
			//			List<CartDTO> cartList = userService.findUserId(loginUser.getUserId());

			CartAllService cartService = new CartAllService();
			List<CartDTO> cartList = cartService.execute(loginUser.getUserId());

			if (cartList == null || cartList.isEmpty()) {
				// ログイン画面（login.jsp）へ遷移させる場合
				request.setAttribute("src", "/ItemsInCartController");
				// 必要に応じてエラーメッセージを表示させたい場合は以下を設定
				request.setAttribute("errorMessage", "カートの中に商品がありません。");

				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/main.jsp");
				dispatcher.forward(request, response);

			} else {
				// ★カート内の商品が1件以上ある場合
				request.setAttribute("cartList", cartList);

				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/purchases.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
}