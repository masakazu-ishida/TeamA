package jp.co.cuatro.service;

import java.sql.Connection;

import jakarta.servlet.ServletException;

import jp.co.cuatro.dao.CartDAO;
import jp.co.cuatro.dto.CartDTO;
import jp.co.cuatro.util.ConnectionUtil;

public class CartAddService {
	public CartDTO execute(CartDTO inputCart) throws ServletException {

		String jndiName = "java:comp/env/jdbc/ecsite";
		try (Connection conn = ConnectionUtil.getConnection(jndiName)) {

			//Connectionをコンストラクタに渡す
			CartDAO dao = new CartDAO(conn);

			CartDTO searchCart = dao.findByUserAndItem(inputCart.getUserId(), inputCart.getItemId());

			if (searchCart != null) {
				int newAmount = searchCart.getAmount() + inputCart.getAmount();

				inputCart.setAmount(newAmount);
				inputCart.setBookedDate(searchCart.getBookedDate());

				dao.update(inputCart);
			} else {

				dao.insert(inputCart);

			}
			return inputCart;
		} catch (Exception e) {
			throw new ServletException(e.getCause());
		}
	}
}
