package jp.co.cuatro.service;

import java.sql.Connection;
import java.util.List;

import jakarta.servlet.ServletException;

import jp.co.cuatro.dao.CartDAO;
import jp.co.cuatro.dto.CartDTO;
import jp.co.cuatro.util.ConnectionUtil;

public class CartAllService {
	public List<CartDTO> execute(String userId) throws ServletException {

		String jndiName = "java:comp/env/jdbc/ecsite";
		try (Connection conn = ConnectionUtil.getConnection(jndiName)) {

			//Connectionをコンストラクタに渡す
			CartDAO dao = new CartDAO(conn);

			List<CartDTO> cartList = dao.findByUserId(userId);

			for (CartDTO cart : cartList) {

				// DTOから「単価(price)」と「数量(amount)」を取り出す
				int price = cart.getItem().getPrice();
				int amount = cart.getAmount();

				// 【計算】単価 × 数量 ＝ 商品ごとの合計金額
				int total = price * amount;

				// 計算した結果を、DTOのtotalにセットする
				cart.setTotal(total);
			}

			return cartList;

		} catch (Exception e) {
			throw new ServletException(e.getCause());
		}
	}
}
