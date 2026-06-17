package jp.co.cuatro.service;

import java.sql.Connection;
import java.util.List;

import jakarta.servlet.ServletException;

import jp.co.cuatro.dao.PurchasesDAO;
import jp.co.cuatro.dto.PurchasesDTO;
import jp.co.cuatro.util.ConnectionUtil;

public class HistoryPurchasesService {

	public List<PurchasesDTO> execute(String userId) throws ServletException {
		String jndiName = "java:comp/env/jdbc/ecsite";
		try (Connection conn = ConnectionUtil.getConnection(jndiName)) {

			PurchasesDAO dao = new PurchasesDAO(conn);

			List<PurchasesDTO> List = dao.historyPurchasesFindByUserId(userId);

			return List;

		} catch (Exception e) {
			throw new ServletException(e.getCause());
		}
	}

}
