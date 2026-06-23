package jp.co.cuatro.service;

import java.sql.Connection;
import java.util.List;

import jp.co.cuatro.dao.CartDAO;
import jp.co.cuatro.dao.ItemDAO;
import jp.co.cuatro.dao.PurchaseDetailsDAO;
import jp.co.cuatro.dao.PurchasesDAO;
import jp.co.cuatro.dto.CartDTO;
import jp.co.cuatro.dto.PurchaseDetailsDTO;
import jp.co.cuatro.dto.PurchasesDTO;
import jp.co.cuatro.util.ConnectionUtil;

public class PurchaseDetailsService {

	public List<CartDTO> execute(String userId, String shippingAddress) {

		String jndiName = "java:comp/env/jdbc/ecsite";

		List<CartDTO> cartList = null;
		try (Connection conn = ConnectionUtil.getConnection(jndiName)) {

			// トランザクション開始
			conn.setAutoCommit(false);

			// トランザクション管理のServiceに渡すのとは別に、ここで一度コネクションを開いて検索する

			CartDAO selectCartDao = new CartDAO(conn);
			cartList = selectCartDao.findByUserId(userId);

			PurchasesDTO purchaseDTO = new PurchasesDTO();
			purchaseDTO.setPurchasedUser(userId); // 注文者ID
			purchaseDTO.setDestination(shippingAddress); // 配送先住所

			PurchasesDAO purchasesDAO = new PurchasesDAO(conn);
			int purchaseId = purchasesDAO.insert(conn, purchaseDTO); // 注文親情報の登録、戻り値にシーケンス購入IDを返す

			PurchaseDetailsDAO purchaseDetailsDAO = new PurchaseDetailsDAO(conn);
			ItemDAO itemDAO = new ItemDAO(conn);

			// カートに入っている数だけループして注文詳細に追加＆在庫更新
			for (CartDTO item : cartList) {
				PurchaseDetailsDTO purchaseDetailsDTO = new PurchaseDetailsDTO();
				purchaseDetailsDTO.setPurchaseId(purchaseId);
				purchaseDetailsDTO.setItemId(item.getItemId());
				purchaseDetailsDTO.setAmount(item.getAmount());
				purchaseDetailsDAO.insert(conn, purchaseDetailsDTO);
				itemDAO.updateMinusStock(conn, item.getItemId(), item.getAmount());
				selectCartDao.deleteCartItem(conn, userId, item.getItemId());
			}

			// すべて成功したら、コミット
			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return cartList;
	}
}