package jp.co.cuatro.service;

import java.sql.Connection;

import jakarta.servlet.ServletException;

import jp.co.cuatro.dao.CartDAO;
import jp.co.cuatro.dto.CartDTO;
import jp.co.cuatro.util.ConnectionUtil;

public class CartDeleteService {

	public CartDTO execute(CartDTO deleteCart) throws ServletException {

		String jndiName = "java:comp/env/jdbc/ecsite";
		try (Connection conn = ConnectionUtil.getConnection(jndiName)) {

			//Connectionをコンストラクタに渡す
			CartDAO dao = new CartDAO(conn);

			CartDTO searchCart = dao.findByUserAndItem(deleteCart.getUserId(), deleteCart.getItemId());

			if (searchCart != null) {

				dao.delete(deleteCart);

			}

			return deleteCart;
		} catch (Exception e) {
			throw new ServletException(e.getCause());
		}
	}

}
