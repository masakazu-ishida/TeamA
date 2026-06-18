package jp.co.cuatro.service;

import java.sql.Connection;

import jakarta.servlet.ServletException;

import jp.co.cuatro.dao.CartDAO;
import jp.co.cuatro.dto.CartDTO;
import jp.co.cuatro.util.ConnectionUtil;

public class CartDeleteConfirmationService {

	public CartDTO execute(CartDTO confirmCart) throws ServletException {

		String jndiName = "java:comp/env/jdbc/ecsite";
		try (Connection conn = ConnectionUtil.getConnection(jndiName)) {

			//Connectionをコンストラクタに渡す
			CartDAO dao = new CartDAO(conn);

			CartDTO searchCart = dao.findByUserAndItem(confirmCart.getUserId(), confirmCart.getItemId());

			if (searchCart != null) {
				confirmCart.setUserId(searchCart.getUserId());
				confirmCart.setItemId(searchCart.getItemId());
				confirmCart.setAmount(searchCart.getAmount());
				confirmCart.setBookedDate(searchCart.getBookedDate());
			}

			return confirmCart;
		}

		catch (Exception e) {
			throw new ServletException(e.getCause());
		}
	}

}
