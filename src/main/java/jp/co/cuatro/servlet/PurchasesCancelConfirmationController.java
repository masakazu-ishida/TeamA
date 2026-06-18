package jp.co.cuatro.servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.cuatro.dto.PurchasesDTO;
import jp.co.cuatro.service.PurchasesCancelConfirmationService;

/**
 * Servlet implementation class PurchasesCancelConfirmationController
 */
@WebServlet("/cancelConfirmation")
public class PurchasesCancelConfirmationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PurchasesCancelConfirmationController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		PurchasesCancelConfirmationService cancelConfirmation = new PurchasesCancelConfirmationService();
		int purchasesId = Integer.parseInt(request.getParameter("purchasesId"));
		PurchasesDTO result = cancelConfirmation.execute(purchasesId);

		request.setAttribute("result", result);

		String path = "/WEB-INF/historyPurchases.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);

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
