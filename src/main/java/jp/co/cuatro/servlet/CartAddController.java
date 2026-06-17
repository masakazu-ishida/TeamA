package jp.co.cuatro.servlet;

import java.io.IOException;
import java.time.LocalDate;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.cuatro.dto.CartDTO;
import jp.co.cuatro.dto.UsersDTO;
import jp.co.cuatro.service.CartAddService;

/**
 * Servlet implementation class CartAddController
 */
@WebServlet("/cartAdd")
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		try {

			String itemId = request.getParameter("itemId");
			String amount = request.getParameter("amount");

			HttpSession session = request.getSession(false);

			// ログイン情報がある場合
			if (session != null && session.getAttribute("loginUser") != null) {

				UsersDTO user = (UsersDTO) session.getAttribute("loginUser");
				String userId = user.getUserId();

				CartDTO inputCart = new CartDTO();

				inputCart.setUserId(userId);
				inputCart.setItemId(Integer.parseInt(itemId));
				inputCart.setAmount(Integer.parseInt(amount));
				inputCart.setBookedDate(LocalDate.now());

				CartAddService svc = new CartAddService();
				CartDTO resultCart = svc.execute(inputCart);

				request.setAttribute("cart", resultCart);

				String path = "/cartDisplay";
				RequestDispatcher rd = request.getRequestDispatcher(path);
				rd.forward(request, response);

			} else {
				request.setAttribute("src", "/cartAdd");
				request.setAttribute("itemId", itemId);
				request.setAttribute("amount", amount);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
				rd.forward(request, response);
			}
		} catch (Exception e) {

			e.printStackTrace();
			throw new ServletException(e);
		}
	}

}
