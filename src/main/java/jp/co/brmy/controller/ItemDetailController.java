package jp.co.brmy.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.brmy.dto.ItemsDTO;
import jp.co.brmy.service.ItemDetailService;

/**
 * Servlet implementation class ItemDetailController
 */
@WebServlet("/itemDetail")
public class ItemDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ItemDetailController() {
		super();
		// TODO Auto-generated constructor stub

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String path1 = "/WEB-INF/jsp/itemDetail.jsp";

		ItemDetailService detail = new ItemDetailService();

		int id = Integer.parseInt(request.getParameter("itemId"));

		ItemsDTO itemsDto = null;

		try {
			itemsDto = detail.findById(id);
		} catch (SQLException | ServletException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();

		}

		request.setAttribute("id", id);
		request.setAttribute("itemsDto", itemsDto);

		RequestDispatcher rd = request.getRequestDispatcher(path1);
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
