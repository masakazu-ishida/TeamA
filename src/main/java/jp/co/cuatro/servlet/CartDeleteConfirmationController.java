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
import jp.co.cuatro.service.CartDeleteConfirmationService;

/**
 * Servlet implementation class CartDeleteConfirmationController
 */
@WebServlet("/cartDeleteConfirmation")
public class CartDeleteConfirmationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CartDeleteConfirmationController() {
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

			if (session != null && session.getAttribute("loginUser") != null) {

				UsersDTO user = (UsersDTO) session.getAttribute("loginUser");
				String userId = user.getUserId();

				String itemIdStr = request.getParameter("itemId");
				int itemId = Integer.parseInt(itemIdStr);

				CartDTO inputCart = new CartDTO();
				inputCart.setUserId(userId);
				inputCart.setItemId(itemId);

				CartDeleteConfirmationService cartDeleteConfirmation = new CartDeleteConfirmationService();
				CartDTO confirmCart = cartDeleteConfirmation.execute(inputCart);

				request.setAttribute("confirmCart", confirmCart);

				String path = "/WEB-INF/cartDeleteConfirmation.jsp";
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
