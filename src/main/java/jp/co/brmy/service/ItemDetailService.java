/**
 * 
 */
package jp.co.brmy.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;

import jp.co.brmy.dao.ItemsDAO;
import jp.co.brmy.dto.ItemsDTO;
import jp.co.brmy.util.ConnectionUtil;

public class ItemDetailService {

	public ItemsDTO findById(int id) throws SQLException, ServletException {
		String lookupstring = "java:comp/env/jdbc/ecsite";
		try (Connection conn = ConnectionUtil.getConnection(lookupstring)) {
			//itemsDAOのfindIdを呼び出し、戻り値をそのままリターン

			ItemsDAO itemsDao = new ItemsDAO(conn);

			ItemsDTO itemsDto = new ItemsDTO();

			itemsDto = itemsDao.findById(id);

			return itemsDto;

		}
	}
}
