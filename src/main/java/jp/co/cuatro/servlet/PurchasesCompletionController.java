package jp.co.cuatro.servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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

			// セッションが無いorログイン情報が無い場合、ログイン画面へ遷移
			if (session == null || session.getAttribute("loginUser") == null) {
				response.sendRedirect(request.getContextPath() + "/login");
			}

			UsersDTO user = (UsersDTO) session.getAttribute("loginUser");
			List<CartDTO> cartList = (List<CartDTO>) session.getAttribute("cartList");

			// カートが空なら、メイン画面にリダイレクト
			//			if (cartList == null || cartList.isEmpty()) {
			//				response.sendRedirect(request.getContextPath() + "/main");
			//				return;
			//			}

			// 配送先の住所を画面から取得
			String destination = request.getParameter("destination");
			String address = request.getParameter("address");

			String shippingAddress = "";

			// 配送先が会員情報に登録された自宅の場合
			if ("registered".equals(destination)) {
				shippingAddress = null;
			} else {
				shippingAddress = address;
			}

			PurchaseDetailsService pds = new PurchaseDetailsService();
			boolean success = pds.execute(user.getUserId(), cartList, shippingAddress);

			if (success) {
				// 成功時は購入完了表示画面へリダイレクト
				response.sendRedirect(request.getContextPath() + "/purchases/success");
			} else {
				// 在庫不足などで失敗した場合は、購入確認画面へフォワード
				request.getRequestDispatcher("/WEB-INF/purchaseSuccess.jsp").forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
