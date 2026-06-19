package jp.co.cuatro.service;

import java.sql.Connection;
import java.util.List;

import jp.co.cuatro.dao.CartDAO;
import jp.co.cuatro.dao.ItemDAO;
import jp.co.cuatro.dao.PurchaseDetailsDAO;
import jp.co.cuatro.dao.PurchasesDAO;
import jp.co.cuatro.dto.CartDTO;
import jp.co.cuatro.dto.ItemDTO;
import jp.co.cuatro.dto.PurchaseDetailsDTO;
import jp.co.cuatro.dto.PurchasesDTO;
import jp.co.cuatro.util.ConnectionUtil;

public class PurchaseDetailsService {

	public boolean execute(String userId, List<CartDTO> cartList, String shippingAddress) {

		boolean success = false;
		String jndiName = "java:comp/env/jdbc/ecsite";

		try (Connection conn = ConnectionUtil.getConnection(jndiName)) {

			// トランザクション開始
			conn.setAutoCommit(false);

			PurchasesDTO purchaseDTO = new PurchasesDTO();
			purchaseDTO.setPurchasedUser(userId); // 注文者ID
			purchaseDTO.setDestination(shippingAddress); // 配送先住所

			PurchasesDAO purchasesDAO = new PurchasesDAO(conn);
			int purchaseId = purchasesDAO.insert(conn, purchaseDTO); // 注文親情報の登録、戻り値にシーケンス購入IDを返す

			PurchaseDetailsDAO purchaseDetailsDAO = new PurchaseDetailsDAO(conn);
			ItemDAO itemDAO = new ItemDAO(conn);
			CartDAO cartDAO = new CartDAO(conn);

			// カートに入っている数だけループして注文詳細に追加＆在庫更新
			for (CartDTO items : cartList) {

				ItemDTO product = itemDAO.findById(items.getItemId());
				// 在庫がカートに追加している数量より少なければ購入不可
				if (product == null || product.getStock() < items.getAmount()) {
					conn.rollback();
					return false;
				}

				PurchaseDetailsDTO purchaseDetailsDTO = new PurchaseDetailsDTO();
				purchaseDetailsDTO.setPurchaseId(purchaseId);
				purchaseDetailsDTO.setItemId(items.getItemId());
				purchaseDetailsDTO.setAmount(items.getAmount());
				purchaseDetailsDAO.insert(conn, purchaseDetailsDTO);
				itemDAO.updateMinusStock(conn, items.getItemId(), items.getAmount());
				cartDAO.deleteCartItem(conn, userId, items.getItemId());
			}

			// すべて成功したら、コミット
			conn.commit();
			success = true; // 戻り値を成功：trueに

		} catch (Exception e) {
			e.printStackTrace();
		}

		return success;
	}
}