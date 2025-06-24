package jp.co.brmy.service;

import java.sql.Connection;

import jp.co.brmy.dao.ItemsInCartDAO;
import jp.co.brmy.dto.ItemsInCartDTO;
import jp.co.brmy.util.CommonConstants;
import jp.co.brmy.util.ConnectionUtil;

public class CartAddService {

	public void cartadd(int itemId, int amount, String userId) throws Exception {

		try (Connection conn = ConnectionUtil.getConnection(CommonConstants.LOOKUP_NAME)) {
			try {
				conn.setAutoCommit(false);
				ItemsInCartDAO dao = new ItemsInCartDAO(conn);
				ItemsInCartDTO dto = new ItemsInCartDTO();
				dto.setAmount(amount);
				dto.setItemId(itemId);
				dto.setUserId(userId);

				dao.insertItemsInCart(dto);

				conn.commit();

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				conn.rollback();
				throw e;
			}

		}

	}
}
