package jp.co.brmy.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.brmy.dto.UsersDTO;
import jp.co.brmy.service.UsersService;

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

		request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//遷移元を判断

		String pass1 = "/brmy/CartDisplayController";
		String pass2 = "";
		String pass3 = "/brmy/main";
		String passLogin = "/WEB-INF/jsp/login.jsp";

		String id = request.getParameter("userId");
		String password = request.getParameter("password");
		String source = request.getParameter("source");

		source = "1";

		UsersService US = new UsersService();

		//userId,password一致する？

		try {
			UsersDTO user = US.findById(id, password);

			if (user == null) {

				//遷移元がカート追加の場合、以下は有効な値がとれるが、それ以外は空が返る
				String amount = request.getParameter("amount");
				String itemId = request.getParameter("itemId");

				request.setAttribute("amount", amount);
				request.setAttribute("itemId", itemId);
				request.setAttribute("source", source);

				request.setAttribute("error", "エラーが発生しました");
				request.getRequestDispatcher(passLogin).forward(request, response);
				return;
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("id", user.getUserId());

			}

		} catch (SQLException |

				ServletException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			return;
		}

		//source == null || source.equals("")
		if (source == null || source.equals("")) {
			//遷移元：タダのログイン表示

			response.sendRedirect(pass3);

		}

		//カート追加からログインに来るとき、hiddenで商品IDと数量が出力されるので、これを取り出す

		else if (source.equals("2")) {
			int amount = Integer.parseInt(request.getParameter("amount"));
			int itemId = Integer.parseInt(request.getParameter("itemId"));

			request.setAttribute("amount", amount);
			request.setAttribute("itemId", itemId);

			RequestDispatcher rd = request.getRequestDispatcher(pass2);
			rd.forward(request, response);

		}

		else if (source.equals("1")) {
			//遷移元：カート一覧だった時の処理    

			response.sendRedirect(pass1);

		}

	}
}