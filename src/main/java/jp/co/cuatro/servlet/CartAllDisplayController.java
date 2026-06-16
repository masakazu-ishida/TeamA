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
 * Servlet implementation class CartAllDisplayController
 */
@WebServlet("/cartDisplay")
public class CartAllDisplayController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CartAllDisplayController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			HttpSession session = request.getSession(false);

			// ログイン情報がある場合
			if (session != null && session.getAttribute("loginUser") != null) {

				UsersDTO user = (UsersDTO) session.getAttribute("loginUser");
				String userId = user.getUserId();

				CartAllService cartDisplay = new CartAllService();
				List<CartDTO> cartList = cartDisplay.execute(userId);

				int grandTotal = 0;
				for (CartDTO c : cartList) {
					grandTotal += c.getTotal();
				}

				request.setAttribute("cartList", cartList);
				request.setAttribute("grandTotal", grandTotal);

				String path = "/WEB-INF/cartAll.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(path);
				rd.forward(request, response);

			} else {
				request.setAttribute("src", "/cartDisplay");
				RequestDispatcher rd = request.getRequestDispatcher("/login");
				rd.forward(request, response);
			}
		} catch (Exception e) {

			e.printStackTrace();
			throw new ServletException(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
