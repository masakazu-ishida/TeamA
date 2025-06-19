package jp.co.brmy.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;

import jp.co.brmy.dao.PurchasesDAO;
import jp.co.brmy.dto.PurchasesDTO;
import jp.co.brmy.util.ConnectionUtil;

public class PurchaseSearchService {

	public List<PurchasesDTO> findByName(String name) throws SQLException, ServletException {
		String lookupstring = "java:comp/env/jdbc/ecsite";
		try (Connection conn = ConnectionUtil.getConnection(lookupstring)) {
			PurchasesDAO dao = new PurchasesDAO(conn);

			return dao.findByName(name);
		}
	}

}
