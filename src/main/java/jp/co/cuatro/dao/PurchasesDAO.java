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

	public List<PurchasesDTO> findPurchaseHistoryByUserId(String userId) throws SQLException {
		String sql = "SELECT p.purchased_date, i.name, i.color, i.manufacturer, i.price, d.amount, p.destination, p.cancel "
				+ "FROM purchases p "
				+ "INNER JOIN purchase_details d ON p.purchase_id = d.purchase_id "
				+ "INNER JOIN items i ON d.item_id = i.item_id "
				+ "WHERE p.purchased_user = ? "
				+ "ORDER BY p.purchased_date DESC, p.purchase_id DESC";

		List<PurchasesDTO> list = new ArrayList<PurchasesDTO>();

		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, userId);

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					PurchasesDTO purchases = new PurchasesDTO();
					PurchaseDetailsDTO purchasesDetails = new PurchaseDetailsDTO();
					ItemDTO item = new ItemDTO();

					purchases.setPurchasedDate(rs.getString("purchased_date"));
					purchases.setDestination(rs.getString("destination"));
					purchases.setCancel(rs.getBoolean("cancel"));

					item.setItemName(rs.getString("name"));
					item.setColor(rs.getString("color"));
					item.setManufacturer(rs.getString("manufacturer"));
					item.setPrice(rs.getInt("price"));

					purchasesDetails.setAmount(rs.getInt("amount"));

					purchasesDetails.setItemDTO(item);
					purchases.setPurchaseDetailsDTO(purchasesDetails);

					list.add(purchases);
				}
			}
		}

		return list;
	}
}
