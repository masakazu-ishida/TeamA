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