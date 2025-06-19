package jp.co.brmy.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;

import jp.co.brmy.dao.PurchasesDAO;
import jp.co.brmy.dto.PurchasesDTO;
import jp.co.brmy.util.ConnectionUtil;

public class DeletePurchaseConfirmService {

	public PurchasesDTO findById(int purchaseId) throws SQLException, ServletException {

		String lookupstring = "java:comp/env/jdbc/ecsite";
		try (Connection conn = ConnectionUtil.getConnection(lookupstring)) {

			PurchasesDAO dao = new PurchasesDAO(conn);

			return dao.findById(purchaseId);
		}
	}
}
