package jp.co.cuatro.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.cuatro.dto.UsersDTO;
import jp.co.cuatro.service.LoginService;

/**
 * Servlet implementation class LoginController
 */
@WebServlet(name = "login", urlPatterns = { "/login" })
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		try {

			String userId = request.getParameter("id");
			String password = request.getParameter("password");

			String path = request.getParameter("src");
			String itemId = request.getParameter("itemId");
			String amount = request.getParameter("amount");

			LoginService service = new LoginService();
			UsersDTO user = service.execute(userId, password);

			if (user != null) { // ログイン情報がある場合
				HttpSession session = request.getSession(true);
				session.setAttribute("loginUser", user);

				if (path == null || path.isEmpty()) { // 遷移元が無く、直接ログインjspが起動されたとき
					response.sendRedirect(request.getContextPath() + "/main");

				} else if (path.equals("/main")) { // メイン画面からログインを押されたとき
					response.sendRedirect(request.getContextPath() + "/main");

				} else if (path.equals("/cartAdd")) { // 商品詳細画面からカートに追加しようとしたときに未ログインで遷移してきたとき

					String redirectUrl = request.getContextPath() + "/cartAdd?itemId=" + itemId + "&amount=" + amount;
					response.sendRedirect(redirectUrl);

				} else if (path.equals("/cartDisplay")) { // メイン画面からカートを見るを押されたとき

					response.sendRedirect(request.getContextPath() + "/cartDisplay");
				} else if (path.equals("/purchases/completion")) { // 商品購入画面から購入ボタンが押されたとき

					response.sendRedirect(request.getContextPath() + "/purchases/completion");
				} else { // その他の場合はメイン画面に遷移　エラー
					response.sendRedirect(request.getContextPath() + "/main");
				}

			} else { // ログイン情報がDBと一致しなかった場合
				request.setAttribute("errorMessage", "ユーザID・パスワードが間違っています");

				request.setAttribute("src", path);
				request.setAttribute("itemId", itemId);
				request.setAttribute("amount", amount);

				String jspPath = "/WEB-INF/error.jsp";
				request.getRequestDispatcher(jspPath).forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);

		}
	}

}
