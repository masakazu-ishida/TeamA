package jp.co.cuatro.servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.cuatro.dto.PurchasesDTO;
import jp.co.cuatro.dto.UsersDTO;
import jp.co.cuatro.service.PurchasesCancelService;

/**
 * Servlet implementation class PurchasesCancelController
 */
@WebServlet("/cancel")
public class PurchasesCancelController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PurchasesCancelController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		UsersDTO loginUser = (UsersDTO) session.getAttribute("loginUser");
		if (loginUser == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/error.jsp");
			dispatcher.forward(request, response);
			return;
		}

		PurchasesCancelService cancel = new PurchasesCancelService();
		int purchaseId = Integer.parseInt(request.getParameter("purchaseId"));
		PurchasesDTO result = cancel.execute(purchaseId);

		request.setAttribute("result", result);

		String path = "/WEB-INF/purchasesCancel.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

}
