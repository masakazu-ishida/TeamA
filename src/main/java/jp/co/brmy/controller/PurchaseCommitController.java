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
import javax.servlet.http.HttpSession;

import jp.co.brmy.dto.ItemsInCartDTO;
import jp.co.brmy.service.PurchaseCommitService;

/**
 * Servlet implementation class PurchaseCommitController
 */
@WebServlet("/PurchaseCommitController")
public class PurchaseCommitController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PurchaseCommitController() {
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
		//		doGet(request, response);
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String pay = request.getParameter("payment");
		String des = request.getParameter("destination");
		String add = request.getParameter("address");

		String juusyo = des;

		if ("another" == des) {
			juusyo = add;
		}else {
			juusyo="ご自宅";
		}
		HttpSession session = request.getSession();
		PurchaseCommitService service = new PurchaseCommitService();
		List<ItemsInCartDTO> list = new ArrayList<>();
		try {
			//			list = service.cartItems(session.getAttribute("user").toString());

			list = service.purchaseCommit("user", pay, juusyo);//後でsessionから取得したユーザー名に変更
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		request.setAttribute("cart", list);
		request.setAttribute("juusyo", juusyo);
		request.setAttribute("paymen", pay);

		String pass = "/WEB-INF/jsp/purchaseCommit.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(pass);
		rd.forward(request, response);

	}

}
