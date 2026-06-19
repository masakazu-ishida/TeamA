package jp.co.cuatro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.cuatro.dto.ItemDTO;

public class ItemDAO {
	private Connection conn;

	public ItemDAO(Connection conn) {
		this.conn = conn;
	}

	public List<ItemDTO> findByCondition(int categoryId, String name) throws SQLException {
		String sql = null;

		if (categoryId == 0) {
			if (name == null || name.isEmpty()) {
				sql = "select item_id, name, color, manufacturer, price, stock, recommended from items;";
			} else {
				sql = "select item_id, name, color, manufacturer, price, stock, recommended from items where name like ?;";
			}
		} else {
			if (name == null || name.isEmpty()) {
				sql = "select item_id, name, color, manufacturer, price, stock, recommended from items where category_id = ?;";
			} else {
				sql = "select item_id, name, color, manufacturer, price, stock, recommended from items where category_id = ? and name like ?;";
			}
		}

		List<ItemDTO> itemsList = new ArrayList<>();

		try (PreparedStatement ps = conn.prepareStatement(sql)) {

			if (categoryId == 0) {
				if (name != null && !name.isEmpty()) {
					ps.setString(1, "%" + name + "%");
				}
			} else {
				if (name == null || name.isEmpty()) {
					ps.setInt(1, categoryId);
				} else {
					ps.setInt(1, categoryId);
					ps.setString(2, "%" + name + "%");
				}
			}

			try (ResultSet rs = ps.executeQuery()) {

				while (rs.next()) {
					ItemDTO item = mapRow(rs);
					itemsList.add(item);
				}
			}
			return itemsList;
		}

	}

	public ItemDTO findById(int itemId) throws SQLException {
		String sql = "SELECT item_id, name, color, manufacturer, price, stock, recommended FROM items where item_id = ?";
		ItemDTO item = null;
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, itemId);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					item = mapRow(rs);
				}
			}
		}
		return item;

	}

	// 1行分のデータをDTOに変換する便利な共通メソッド
	private ItemDTO mapRow(ResultSet rs) throws SQLException {
		ItemDTO item = new ItemDTO();

		item.setItemId(rs.getInt("item_id"));
		item.setItemName(rs.getString("name"));
		item.setColor(rs.getString("color"));
		item.setManufacturer(rs.getString("manufacturer"));
		item.setPrice(rs.getInt("price"));
		item.setStock(rs.getInt("stock"));
		item.setRecommended(rs.getBoolean("recommended"));

		return item;

	}

	public void updatePlusStock(int itemId, int amount) throws SQLException {

		String sql = "UPDATE items SET stock = stock + ? WHERE item_id = ?";

		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, amount);
			ps.setInt(2, itemId);
			ps.executeUpdate();
		}
	}

	// 中瀬が作っている最中です
	public void updateMinusStock(Connection conn, int itemId, int amount) throws SQLException {

		// 在庫から購入数を引き算するSQL
		String sql = "UPDATE items SET stock = stock - ? WHERE item_id = ?";

		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, amount);
			ps.setInt(2, itemId);
			ps.executeUpdate();
		}
	}
}
