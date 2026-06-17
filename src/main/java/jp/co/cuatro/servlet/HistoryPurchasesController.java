package jp.co.cuatro.servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.cuatro.dto.PurchasesDTO;
import jp.co.cuatro.dto.UsersDTO;
import jp.co.cuatro.service.HistoryPurchasesService;

/**
 * Servlet implementation class HistoryPurchasesController
 */
@WebServlet("/history")
public class HistoryPurchasesController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HistoryPurchasesController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		try {
			HttpSession session = request.getSession(false);

			UsersDTO user = (UsersDTO) session.getAttribute("loginUser");
			String userId = user.getUserId();

			HistoryPurchasesService historyPurchases = new HistoryPurchasesService();
			List<PurchasesDTO> list = historyPurchases.execute(userId);

			request.setAttribute("List", list);

			String path = "/WEB-INF/historyPurchases.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);

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
