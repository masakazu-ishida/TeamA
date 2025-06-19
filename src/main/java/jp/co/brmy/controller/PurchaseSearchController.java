package jp.co.brmy.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.brmy.dto.PurchasesDTO;
import jp.co.brmy.service.PurchaseSearchService;

/**
 * Servlet implementation class PurchaseSerchController
 */
@WebServlet(name = "PurchaseSearchController", urlPatterns = { "/PurchaseSearchController" })
public class PurchaseSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PurchaseSearchController() {
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
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String path1 = "/WEB-INF/jsp/adminmain.jsp";
		String path2 = "/WEB-INF/jsp/purchaseSearch.jsp";
		String name = request.getParameter("userId");

		PurchaseSearchService service = new PurchaseSearchService();
		List<PurchasesDTO> list = new ArrayList<>();
		try {
			list = service.findByName(name);

			if (list.size() == 0) {
				RequestDispatcher rd = request.getRequestDispatcher(path1);
				rd.forward(request, response);
			} else {
				request.setAttribute("purchas", list);
				RequestDispatcher rd = request.getRequestDispatcher(path2);
				rd.forward(request, response);
			}
		} catch (Exception e) {
		}
	}
}
