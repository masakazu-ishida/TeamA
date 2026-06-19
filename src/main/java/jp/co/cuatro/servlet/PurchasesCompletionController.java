package jp.co.cuatro.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.cuatro.dao.CartDAO;
import jp.co.cuatro.dto.CartDTO;
import jp.co.cuatro.dto.UsersDTO;
import jp.co.cuatro.service.PurchaseDetailsService;

/**
 * Servlet implementation class PurchasesCompletionController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/purchases/completion" })
public class PurchasesCompletionController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PurchasesCompletionController() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// もし間違えてGETでパスが叩かれたとき
		response.sendRedirect(request.getContextPath() + "/main");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		try {

			HttpSession session = request.getSession(false);

			// セッションチェック
			if (session == null || session.getAttribute("loginUser") == null) {
				// ログインセッションがない場合、メイン画面へ遷移
				response.sendRedirect(request.getContextPath() + "/login");
				return;
			}

			UsersDTO user = (UsersDTO) session.getAttribute("loginUser");

			List<CartDTO> cartList = null;
			String jndiName = "java:comp/env/jdbc/ecsite";

			// トランザクション管理のServiceに渡すのとは別に、ここで一度コネクションを開いて検索する
			try (Connection conn = jp.co.cuatro.util.ConnectionUtil.getConnection(jndiName)) {
				CartDAO selectCartDao = new CartDAO(conn);
				cartList = selectCartDao.findByUserId(user.getUserId());
			}

			// 配送先の住所を画面から取得
			String destination = request.getParameter("destination");
			String address = request.getParameter("address");

			String shippingAddress = "";

			// 配送先判定
			if ("registered".equals(destination)) {
				shippingAddress = null;
			} else {
				shippingAddress = address;
			}

			PurchaseDetailsService pds = new PurchaseDetailsService();
			boolean success = pds.execute(user.getUserId(), cartList, shippingAddress);

			if (success) {
				// JSPで表示するために、購入した内容をリクエストに詰める
				request.setAttribute("cartList", cartList);
				request.setAttribute("paymentMethod", request.getParameter("paymentMethod"));
				request.setAttribute("destination", request.getParameter("destination"));
				request.setAttribute("address", request.getParameter("address"));

				// 成功時は購入完了表示画面へフォワード
				request.getRequestDispatcher("/WEB-INF/purchasesSuccess.jsp").forward(request, response);
			} else {
				// 失敗時は理由を添えて購入確認画面へフォワード
				request.setAttribute("cartList", cartList);
				request.setAttribute("paymentMethod", request.getParameter("paymentMethod"));
				request.setAttribute("destination", request.getParameter("destination"));
				request.setAttribute("address", request.getParameter("address"));

				request.setAttribute("errorMsg", "購入処理に失敗しました。在庫数などを確認してください。");
				request.getRequestDispatcher("/WEB-INF/purchases.jsp").forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
