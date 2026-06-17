package jp.co.cuatro.servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.cuatro.dto.ItemDTO;
import jp.co.cuatro.service.ItemsDetailService;

/**
 * Servlet implementation class ItemsDetailController
 */
@WebServlet("/itemsDetail")
public class ItemsDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ItemsDetailController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		int itemId = Integer.parseInt(request.getParameter("itemId"));

		ItemsDetailService itemsDetailService = new ItemsDetailService();

		ItemDTO itemsDetail = itemsDetailService.execute(itemId);

		request.setAttribute("itemsDetail", itemsDetail);

		String path = "/WEB-INF/itemsDetailResult.jsp";
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
