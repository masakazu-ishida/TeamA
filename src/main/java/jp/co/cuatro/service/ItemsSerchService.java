package jp.co.cuatro.service;

import java.sql.Connection;
import java.util.List;

import jakarta.servlet.ServletException;

import jp.co.cuatro.dao.ItemDAO;
import jp.co.cuatro.dto.ItemDTO;
import jp.co.cuatro.util.ConnectionUtil;

public class ItemsSerchService {

	public List<ItemDTO> execute(int categoryId, String name) throws ServletException {
		String jndiName = "java:comp/env/jdbc/ecsite";
		try (Connection conn = ConnectionUtil.getConnection(jndiName)) {

			ItemDAO dao = new ItemDAO(conn);

			List<ItemDTO> itemsList = dao.findByCondition(categoryId, name);

			return itemsList;

		} catch (Exception e) {
			throw new ServletException(e.getCause());
		}
	}
}
