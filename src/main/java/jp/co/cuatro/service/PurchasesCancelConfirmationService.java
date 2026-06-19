package jp.co.cuatro.service;

import java.sql.Connection;

import jakarta.servlet.ServletException;

import jp.co.cuatro.dao.PurchasesDAO;
import jp.co.cuatro.dto.PurchasesDTO;
import jp.co.cuatro.util.ConnectionUtil;

public class PurchasesCancelConfirmationService {
	public PurchasesDTO execute(int purchaseId) throws ServletException {
		String jndiName = "java:comp/env/jdbc/ecsite";
		try (Connection conn = ConnectionUtil.getConnection(jndiName)) {

			PurchasesDAO dao = new PurchasesDAO(conn);

			PurchasesDTO result = dao.findById(purchaseId);

			return result;

		} catch (Exception e) {
			throw new ServletException(e.getCause());
		}

	}
}
