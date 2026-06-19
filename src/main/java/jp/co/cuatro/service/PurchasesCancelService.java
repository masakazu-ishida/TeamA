package jp.co.cuatro.service;

import java.sql.Connection;

import jakarta.servlet.ServletException;

import jp.co.cuatro.dao.ItemDAO;
import jp.co.cuatro.dao.PurchasesDAO;
import jp.co.cuatro.dto.PurchaseDetailsDTO;
import jp.co.cuatro.dto.PurchasesDTO;
import jp.co.cuatro.util.ConnectionUtil;

public class PurchasesCancelService {
	public PurchasesDTO execute(int purchasesId) throws ServletException {
		String jndiName = "java:comp/env/jdbc/ecsite";

		try (Connection conn = ConnectionUtil.getConnection(jndiName)) {

			PurchasesDAO pDao = new PurchasesDAO(conn);
			ItemDAO iDao = new ItemDAO(conn);

			PurchasesDTO result = pDao.findById(purchasesId);

			conn.setAutoCommit(false);
			try {

				pDao.updatePurchaseCancel(purchasesId);

				for (PurchaseDetailsDTO p : result.getDetailsList()) {

					int itemId = p.getItemDTO().getItemId();
					int amount = p.getAmount();
					iDao.updatePlusStock(itemId, amount);
				}
				conn.commit();

			} catch (Exception e) {
				e.printStackTrace();
				conn.rollback();
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}

	}
}