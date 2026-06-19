package jp.co.cuatro.servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.cuatro.dto.CartDTO;
import jp.co.cuatro.dto.UsersDTO;
import jp.co.cuatro.service.CartDeleteService;

/**
 * Servlet implementation class CartDeleteController
 */
@WebServlet("/cartDelete")
public class CartDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CartDeleteController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			HttpSession session = request.getSession(false);

			// ログイン情報がある場合
			if (session != null && session.getAttribute("loginUser") != null) {

				UsersDTO user = (UsersDTO) session.getAttribute("loginUser");
				String userId = user.getUserId();

				String itemIdStr = request.getParameter("itemId");
				int itemId = Integer.parseInt(itemIdStr);

				CartDTO inputCart = new CartDTO();

				inputCart.setUserId(userId);
				inputCart.setItemId(itemId);

				CartDeleteService cartDelete = new CartDeleteService();
				CartDTO deleteCart = cartDelete.execute(inputCart);

				request.setAttribute("searchCart", deleteCart);

				String path = "/WEB-INF/cartDelete.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(path);
				rd.forward(request, response);

			} else {
				RequestDispatcher rd = request.getRequestDispatcher("/error");
				rd.forward(request, response);
			}
		} catch (Exception e) {

			e.printStackTrace();
			throw new ServletException(e);
		}
	}

}
