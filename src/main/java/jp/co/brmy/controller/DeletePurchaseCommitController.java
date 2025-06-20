package jp.co.brmy.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.brmy.dto.PurchasesDTO;
import jp.co.brmy.service.DeletePurchaseCommitService;

/**
 * Servlet implementation class DeletePurchaseCommitController
 */
@WebServlet(name = "DeletePurchaseCommitController", urlPatterns = { "/DeletePurchaseCommitController" })
public class DeletePurchaseCommitController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeletePurchaseCommitController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String purchaseIdParam = request.getParameter("purchaseId");
		String path = "/WEB-INF/jsp/deletePurchaseCommit.jsp";

		int purchaseId = Integer.parseInt(purchaseIdParam);
		DeletePurchaseCommitService dpcs = new DeletePurchaseCommitService();
		PurchasesDTO dto = new PurchasesDTO();

		try {
			dto = dpcs.cancel(purchaseId);

		} catch (Exception e) {
			// TODO: handle exception
		}
		request.setAttribute("dto", dto);
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
