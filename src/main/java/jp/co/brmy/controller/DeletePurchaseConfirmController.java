package jp.co.brmy.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.brmy.dto.PurchasesDTO;
import jp.co.brmy.service.DeletePurchaseConfirmService;

/**
 * Servlet implementation class DeletePurchaseConfirmController
 */
@WebServlet(name = "DeletePurchaseConfirmController", urlPatterns = { "/DeletePurchaseConfirmController" })
public class DeletePurchaseConfirmController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeletePurchaseConfirmController() {
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
		String path = "/WEB-INF/jsp/deletePurchaseConfirm.jsp";

		int purchaseId = Integer.parseInt(purchaseIdParam);

		DeletePurchaseConfirmService dpcc = new DeletePurchaseConfirmService();
		PurchasesDTO dto = new PurchasesDTO();

		try {
			dto = dpcc.findById(purchaseId);

		} catch (SQLException | ServletException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
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
