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
 * Servlet implementation class MainController
 */
@WebServlet("/main")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MainController() {
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
		//doGet(request, response);

		String path1 = "/WEB-INF/jsp/searchResult.jsp";

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		//画面から入力されたカテゴリー番号とキーワードを取り出す
		String keyword = request.getParameter("keyword");
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));

		//サービスに検索処理の依頼をする
		//アイテムサービスクラスをインスタンス化する
		ItemsService Itemsservice = new ItemsService();

		//インスタンスに対してfindnamesearchを呼び出す
		//findnamesearchの戻り値を取得し、それをsetattribureでjspに渡す

		List<ItemsDTO> itemsDto = new ArrayList<>();

		try {
			itemsDto = Itemsservice.findNameSearch(keyword, categoryId);
			//String name = Itemservice.categoryName(caregoryId);

		} catch (SQLException | ServletException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		//jspにforward
		request.setAttribute("keyword", keyword);
		request.setAttribute("categoryId", categoryId);
		request.setAttribute("categoryName",);
		request.setAttribute("itemsDto", itemsDto);
		RequestDispatcher rd = request.getRequestDispatcher(path1);
		rd.forward(request, response);

	}

}
