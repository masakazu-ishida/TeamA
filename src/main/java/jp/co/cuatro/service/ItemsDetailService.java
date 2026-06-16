package jp.co.cuatro.service;

import java.sql.Connection;

import jakarta.servlet.ServletException;

import jp.co.cuatro.dao.ItemDAO;
import jp.co.cuatro.dto.ItemDTO;
import jp.co.cuatro.util.ConnectionUtil;

public class ItemsDetailService {
	public ItemDTO execute(int itemId) throws ServletException {
		String jndiName = "java:comp/env/jdbc/ecsite";
		try (Connection conn = ConnectionUtil.getConnection(jndiName)) {

			ItemDAO dao = new ItemDAO(conn);

			ItemDTO itemsDetail = dao.findById(itemId);

			return itemsDetail;

		} catch (Exception e) {
			throw new ServletException(e.getCause());
		}
	}

}
