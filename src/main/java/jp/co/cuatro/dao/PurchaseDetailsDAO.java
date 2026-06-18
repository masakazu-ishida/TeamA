package jp.co.cuatro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.cuatro.dto.PurchaseDetailsDTO;

public class PurchaseDetailsDAO {
	private Connection con;

	public PurchaseDetailsDAO(Connection con) {
		this.con = con;
	}

	/**
	 * 特定の購入ID（purchase_id）に紐づく購入明細の一覧を取得します。
	 * （ユーザーの「注文履歴詳細」などで使用します）
	 * * @param purchaseId 検索対象の購入ID
	 * @return 購入明細DTOのリスト
	 * @throws SQLException
	 */
	public List<PurchaseDetailsDTO> findByPurchaseId(int purchaseId) throws SQLException {
		List<PurchaseDetailsDTO> detailList = new ArrayList<>();

		// purchase_detailsテーブルから指定されたpurchase_idのデータを取得するSQL
		String sql = """
				SELECT purchase_detail_id, purchase_id, item_id, amount \
				FROM purchase_details WHERE purchase_id = ? \
				ORDER BY purchase_detail_id ASC""";

		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, purchaseId);

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					PurchaseDetailsDTO dto = new PurchaseDetailsDTO();
					dto.setPurchaseDetailId(rs.getInt("purchase_detail_id"));
					dto.setPurchaseId(rs.getInt("purchase_id"));
					dto.setItemId(rs.getInt("item_id"));
					dto.setAmount(rs.getInt("amount"));

					detailList.add(dto);
				}
			}
		}
		return detailList;
	}

	// 中瀬が作っている最中です
	public void insert(Connection conn, PurchaseDetailsDTO purchaseDetailsDTO) throws SQLException {

		String sql = "INSERT INTO purchase_details (purchase_id, item_id, amount) "
				+ "VALUES (?, ?, ?)";

		try (PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, purchaseDetailsDTO.getPurchaseId()); // 紐付ける注文ID
			ps.setInt(2, purchaseDetailsDTO.getItemId()); // 商品ID
			ps.setInt(3, purchaseDetailsDTO.getAmount()); // 数量

			// SQLの実行
			ps.executeUpdate();
		}
	}
}