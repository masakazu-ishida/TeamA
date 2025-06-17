package jp.co.brmy.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import jp.co.brmy.dao.ItemsDAO;
import jp.co.brmy.dto.ItemsDTO;
import jp.co.brmy.util.ConnectionUtil;

public class ItemsService {

	public List<ItemsDTO> findNameSearch(String name, int id) throws SQLException, ServletException {
		String lookupstring = "java:comp/env/jdbc/ecsite";
		try (Connection conn = ConnectionUtil.getConnection(lookupstring)) {
			//itemsDAOのfindnamesearchを呼び出し、戻り値をそのままリターン

			ItemsDAO itemsDao = new ItemsDAO(conn);

			List<ItemsDTO> itemsDto = new ArrayList<>();

			//キーワードが非空かつ、カテゴリ名が全部の場合
			if ((name != null && null != "") && id == 0) {
				itemsDto = itemsDao.findNameSearch(name);
			}

			//キーワードが非空かつ、カテゴリ名が何か指定されている場合
			else if ((name != null && null != "") && id != 0) {
				itemsDto = itemsDao.findNameSearch(name, id);
			}

			//キーワードが空白かつ、カテゴリ名が何か指定されている場合
			else if ((name == "" || name == null) && id != 0) {
				itemsDto = itemsDao.findNameSearch(id);
			}

			//キーワードが空白かつ、カテゴリ名が全部の場合
			else if ((name == "" || name == null) && id == 0) {
				itemsDto = itemsDao.findAll();
			}

			return itemsDto;

		}
	}

}
