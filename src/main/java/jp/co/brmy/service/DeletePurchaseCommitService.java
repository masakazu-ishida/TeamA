package jp.co.brmy.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;

import jp.co.brmy.dao.ItemsDAO;
import jp.co.brmy.dao.PurchasesDAO;
import jp.co.brmy.dto.PurchaseDetailsDTO;
import jp.co.brmy.dto.PurchasesDTO;
import jp.co.brmy.util.ConnectionUtil;

public class DeletePurchaseCommitService {

	public PurchasesDTO cancel(int purchaseId) throws SQLException, ServletException {

		String lookupstring = "java:comp/env/jdbc/ecsite";
		try (Connection conn = ConnectionUtil.getConnection(lookupstring)) {

			PurchasesDAO purchaseDAO = new PurchasesDAO(conn);
			ItemsDAO itemsDAO = new ItemsDAO(conn);

			PurchasesDTO purchaseDTO = purchaseDAO.findById(purchaseId);

			try {
				conn.setAutoCommit(false);
				purchaseDAO.update(purchaseId);

				for (PurchaseDetailsDTO detailDTO : purchaseDTO.getPurchaseDetailsDTO()) {
					itemsDAO.updateStock(detailDTO.getItemsDTO().getStock() + detailDTO.getAmount(),
							detailDTO.getItemId());

				}

				conn.commit();
			} catch (Exception e) {
				// TODO: handle exception
				conn.rollback();
			}
			return purchaseDTO;

		}
	}
}
