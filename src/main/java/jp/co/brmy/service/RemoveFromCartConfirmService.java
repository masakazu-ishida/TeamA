package jp.co.brmy.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;

import jp.co.brmy.dao.ItemsInCartDAO;
import jp.co.brmy.dto.ItemsInCartDTO;
import jp.co.brmy.util.CommonConstants;
import jp.co.brmy.util.ConnectionUtil;

public class RemoveFromCartConfirmService {

	public ItemsInCartDTO findById(String userId, int itemId) throws SQLException, ServletException {

		try (Connection conn = ConnectionUtil.getConnection(CommonConstants.LOOKUP_NAME)) {
			ItemsInCartDAO dao = new ItemsInCartDAO(conn);

			return dao.findById(userId, itemId);

		}
	}
}
