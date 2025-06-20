package jp.co.brmy.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.brmy.dto.ItemsDTO;
import jp.co.brmy.service.ItemsService;

/**
 * Servlet implementation class MainController2
 */
@WebServlet("/main2")
public class MainController2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MainController2() {
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

		String path1 = "/WEB-INF/jsp/searchResult.jsp";

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		//画面から入力されたカテゴリー番号とキーワード、スタート番号を取り出す
		String keyword = request.getParameter("keyword");
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));

		String categoryName = request.getParameter("categoryName");

		//サービスに検索処理の依頼をする
		//アイテムサービスクラスをインスタンス化する
		ItemsService Itemsservice = new ItemsService();

		//インスタンスに対してfindnamesearchを呼び出す
		//findnamesearchの戻り値を取得し、それをsetattribureでjspに渡す

		List<ItemsDTO> itemsDto = new ArrayList<>();
		String name = null;

		try {
			itemsDto = Itemsservice.findNameSearchByLimit(keyword, categoryId, pageNumber);
			name = Itemsservice.categoryName(categoryId);

		} catch (SQLException | ServletException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();

		}

		//jspにforward
		request.setAttribute("keyword", keyword);
		request.setAttribute("categoryId", categoryId);
		request.setAttribute("categoryName", name);
		request.setAttribute("itemsDto", itemsDto);
		request.setAttribute("pageNumber", pageNumber);

		RequestDispatcher rd = request.getRequestDispatcher(path1);
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);

	}

}
