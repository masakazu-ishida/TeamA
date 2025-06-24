package jp.co.brmy.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.brmy.dto.ItemsInCartDTO;
import jp.co.brmy.service.RemoveFromCartConfirmService;

/**
 * Servlet implementation class ItemsInCartDelCheckController
 */
@WebServlet("/RemoveFromCartConfirm")
public class RemoveFromCartConfirmController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RemoveFromCartConfirmController() {
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

		String path = "/WEB-INF/jsp/removeFromCartConfirm.jsp";

		int itemId = Integer.parseInt(request.getParameter("itemId"));
		request.setAttribute("itemId", itemId);

		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("user");

		RemoveFromCartConfirmService serv = new RemoveFromCartConfirmService();
		ItemsInCartDTO dto = new ItemsInCartDTO();

		try {
			dto = serv.findById(userId, itemId);

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
