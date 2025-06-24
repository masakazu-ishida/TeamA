package jp.co.brmy.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;

import jp.co.brmy.dao.ItemsDAO;
import jp.co.brmy.dao.ItemsInCartDAO;
import jp.co.brmy.dao.PurchaseDetailsDAO;
import jp.co.brmy.dao.PurchasesDAO;
import jp.co.brmy.dto.ItemsDTO;
import jp.co.brmy.dto.ItemsInCartDTO;
import jp.co.brmy.dto.PurchaseDetailsDTO;
import jp.co.brmy.dto.PurchasesDTO;
import jp.co.brmy.util.CommonConstants;
import jp.co.brmy.util.ConnectionUtil;

public class PurchaseCommitService {

	public List<ItemsInCartDTO> purchaseCommit(String user, String seisan, String haisou)
			throws SQLException, ServletException {

		try (Connection conn = ConnectionUtil.getConnection(CommonConstants.LOOKUP_NAME)) {

			ItemsInCartDAO itemInCartDAO = new ItemsInCartDAO(conn);
			PurchasesDAO purchasesDAO = new PurchasesDAO(conn);
			PurchaseDetailsDAO purchaseDetailsDAO = new PurchaseDetailsDAO(conn);
			ItemsInCartDAO itemsInCartDAO = new ItemsInCartDAO(conn);
			ItemsDAO itemsDAO = new ItemsDAO(conn);
			List<ItemsInCartDTO> cartdto = itemInCartDAO.findByUserId(user);

			conn.setAutoCommit(false);

			List<PurchasesDTO> list = purchasesDAO.findAll();
			int id = list.size() + 1;

			try {
				PurchasesDTO purchasesDTO = new PurchasesDTO();
				purchasesDTO.setDestination(haisou);
				Date now = new Date();
				purchasesDTO.setPurchaseDate(now);

				purchasesDTO.setPurchaseUser(user);
				purchasesDAO.insert(purchasesDTO);

				for (ItemsInCartDTO itemsInCartDTO : cartdto) {
					PurchaseDetailsDTO purchaseDetailsDTO = new PurchaseDetailsDTO();
					purchaseDetailsDTO.setPurchaseId(id);
					purchaseDetailsDTO.setItemId(itemsInCartDTO.getItemId());
					purchaseDetailsDTO.setAmount(itemsInCartDTO.getAmount());

					purchaseDetailsDAO.insert(purchaseDetailsDTO);

					ItemsDTO itemsDTO = itemsDAO.findById(itemsInCartDTO.getItemId());
					int a = itemsDTO.getStock() - itemsInCartDTO.getAmount();
					itemsDAO.updateStock(a, itemsDTO.getItemId());

				}
				itemInCartDAO.deleteItemsInCart(user);

				conn.commit();
			} catch (Exception e) {
				e.printStackTrace();
				conn.rollback();
			}
			return cartdto;

		}
	}
}
