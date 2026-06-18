package jp.co.cuatro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.cuatro.dto.ItemDTO;
import jp.co.cuatro.dto.PurchaseDetailsDTO;
import jp.co.cuatro.dto.PurchasesDTO;

public class PurchasesDAO {
	private Connection con;

	public PurchasesDAO(Connection con) {
		this.con = con;
	}

	public List<PurchasesDTO> historyPurchasesFindByUserId(String userId) throws SQLException {
		String sql = """
				SELECT p.purchase_id, p.purchased_user, p.purchased_date,  p.destination, p.cancel,\
				d.purchase_detail_id,  d.purchase_id,  d.item_id,  d.amount,\
				i.item_id, i.name, i.manufacturer, i.category_id, i.color, i.price, i.stock, i.recommended\
				 FROM purchases p \
				INNER JOIN purchase_details d ON p.purchase_id = d.purchase_id \
				INNER JOIN items i ON d.item_id = i.item_id \
				WHERE p.purchased_user = ? \
				ORDER BY p.purchased_date DESC, p.purchase_id DESC""";

		List<PurchasesDTO> list = new ArrayList<>();

		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, userId);

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					int nowPurchaseId = rs.getInt("purchase_id");

					PurchasesDTO purchases = null;
					for (PurchasesDTO p : list) {
						if (p.getPurchaseId() == nowPurchaseId) {
							purchases = p;
							break;
						}
					}

					if (purchases == null) {
						purchases = new PurchasesDTO();
						purchases.setPurchaseId(rs.getInt("purchase_id"));
						purchases.setPurchasedUser(rs.getString("purchased_user"));
						purchases.setPurchasedDate(rs.getString("purchased_date"));
						purchases.setDestination(rs.getString("destination"));
						purchases.setCancel(rs.getBoolean("cancel"));

						list.add(purchases);
					}

					PurchaseDetailsDTO purchasesDetails = new PurchaseDetailsDTO();
					purchasesDetails.setPurchaseDetailId(rs.getInt("purchase_detail_id"));
					purchasesDetails.setPurchaseId(rs.getInt("purchase_id"));
					purchasesDetails.setItemId(rs.getInt("item_id"));
					purchasesDetails.setAmount(rs.getInt("amount"));

					ItemDTO item = new ItemDTO();
					item.setItemId(rs.getInt("item_id"));
					item.setItemName(rs.getString("name"));
					item.setManufacturer(rs.getString("manufacturer"));
					item.setCategoryId(rs.getInt("category_id"));
					item.setColor(rs.getString("color"));
					item.setPrice(rs.getInt("price"));
					item.setStock(rs.getInt("stock"));
					item.setRecommended(rs.getBoolean("recommended"));

					purchasesDetails.setItemDTO(item);
					purchases.getDetailsList().add(purchasesDetails);
				}
			}
		}

		return list;
	}

	public List<PurchasesDTO> purchasesCancelConfirmationFindByPurchaseId(int purchaseId) throws SQLException {
		String sql = """
				SELECT p.purchase_id, p.purchased_user, p.purchased_date,  p.destination, p.cancel,\
				d.purchase_detail_id,  d.purchase_id,  d.item_id,  d.amount,\
				i.item_id, i.name, i.manufacturer, i.category_id, i.color, i.price, i.stock, i.recommended\
				 FROM purchases p \
				INNER JOIN purchase_details d ON p.purchase_id = d.purchase_id \
				INNER JOIN items i ON d.item_id = i.item_id \
				WHERE p.purchase_id = ? \
				ORDER BY p.purchased_date DESC, p.purchase_id DESC""";

		List<PurchasesDTO> list = new ArrayList<>();

		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, purchaseId);

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					int nowPurchaseId = rs.getInt("purchase_id");

					PurchasesDTO purchases = null;
					for (PurchasesDTO p : list) {
						if (p.getPurchaseId() == nowPurchaseId) {
							purchases = p;
							break;
						}
					}

					if (purchases == null) {
						purchases = new PurchasesDTO();
						purchases.setPurchaseId(rs.getInt("purchase_id"));
						purchases.setPurchasedUser(rs.getString("purchased_user"));
						purchases.setPurchasedDate(rs.getString("purchased_date"));
						purchases.setDestination(rs.getString("destination"));
						purchases.setCancel(rs.getBoolean("cancel"));

						list.add(purchases);
					}

					PurchaseDetailsDTO purchasesDetails = new PurchaseDetailsDTO();
					purchasesDetails.setPurchaseDetailId(rs.getInt("purchase_detail_id"));
					purchasesDetails.setPurchaseId(rs.getInt("purchase_id"));
					purchasesDetails.setItemId(rs.getInt("item_id"));
					purchasesDetails.setAmount(rs.getInt("amount"));

					ItemDTO item = new ItemDTO();
					item.setItemId(rs.getInt("item_id"));
					item.setItemName(rs.getString("name"));
					item.setManufacturer(rs.getString("manufacturer"));
					item.setCategoryId(rs.getInt("category_id"));
					item.setColor(rs.getString("color"));
					item.setPrice(rs.getInt("price"));
					item.setStock(rs.getInt("stock"));
					item.setRecommended(rs.getBoolean("recommended"));

					purchasesDetails.setItemDTO(item);
					purchases.getDetailsList().add(purchasesDetails);
				}
			}
		}

		return list;
	}

	// 中瀬が作っている最中です
	public int insert() throws SQLException {
		String sql = "INSERT INTO purchases (purchased_user, purchased_date, destination, cancel) "
				+ "VALUES (?, CURRENT_DATE, ?, false)";

		try (PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
			int id = 0;
			PurchasesDTO purchasesDTO = new PurchasesDTO();
			ps.setString(1, purchasesDTO.getPurchasedUser()); // 注文者ID
			ps.setString(2, purchasesDTO.getDestination()); // 配送先住所

			// 4. SQLを実行（この瞬間にDB側のシーケンスがガチャンと動いてIDが自動採番される）
			ps.executeUpdate();
			return id;
		}
	}
}

//package jp.co.cuatro.dao;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import jp.co.cuatro.dto.CartDTO;
//
//public class PurchasesDAO {
//	private Connection con;
//
//	public PurchasesDAO(Connection con) {
//		this.con = con;
//
//	}
//
//	public List<CartDTO> buy(String userId) throws SQLException {
//		List<CartDTO> purchasedList = new ArrayList<>();
//		// SQL文の定義
//		String insertPurchaseSql = "INSERT INTO purchases (user_id,purchased_date, ,destination, cancel) VALUES (?, CURRENT_DATE, null, false)";
//		String updateStockSql = "UPDATE items SET stock = stock - ? WHERE item_id = ? AND stock >= ?";
//		String deleteCartSql = "DELETE FROM items_in_cart WHERE user_id = ?";
//
//		try (
//				PreparedStatement psPurchase = con.prepareStatement(insertPurchaseSql);
//				PreparedStatement psUpdateStock = con.prepareStatement(updateStockSql);
//				PreparedStatement psDeleteCart = con.prepareStatement(deleteCartSql);) {
//			psPurchase.setString(1, userId);
//			int insertList = psPurchase.executeUpdate();
//			for (CartDTO item : purchasedList) {
//
//				// ① 在庫の減算 (現在の在庫が購入数以上ある場合のみUPDATEが成功する)
//				psUpdateStock.setInt(1, item.getAmount());
//				psUpdateStock.setInt(2, item.getItemId());
//				psUpdateStock.setInt(3, item.getAmount()); // stock >= ? 用
//
//				int stockUpdated = psUpdateStock.executeUpdate();
//				if (stockUpdated == 0) {
//					// 更新件数が0＝在庫が足りない
//					throw new SQLException("商品ID: " + item.getItemId() + " の在庫が不足しています。");
//				}
//
//				// ② purchase_detailsテーブル（子）へのインサート
//				psInsertDetail.setInt(1, PurchaseId); // 処理Cで取得した親ID
//				psInsertDetail.setInt(2, item.getItemId());
//				psInsertDetail.setInt(3, item.getAmount());
//				psInsertDetail.executeUpdate();
//			}
//
//			// --- 処理E: 購入がすべて終わったのでカートを空にする ---
//			psDeleteCart.setString(1, userId);
//			psDeleteCart.executeUpdate();
//
//			// 🚀 すべて無事に成功したら、ここでデータベースに確定反映（コミット）
//			con.commit();
//
//		} catch (SQLException e) {
//			// ❌ 途中で1つでもエラーが発生したら、すべての処理を取り消す（ロールバック）
//			System.out.println("実行中にエラーが発生したため、ロールバックしました。");
//			e.printStackTrace();
//			if (con != null) {
//				try {
//					con.rollback();
//				} catch (SQLException ex) {
//					ex.printStackTrace();
//				}
//			}
//			return null;
//		} finally {
//			// 次の処理のためにオートコミットの設定をデフォルト(true)に戻しておく
//			if (con != null) {
//				con.setAutoCommit(true);
//			}
//		}
//
//		// 購入された商品の入ったリストを返す
//		return purchasedList;
//		}
//	}
//}
