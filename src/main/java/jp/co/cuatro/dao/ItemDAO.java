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

		if (categoryId == 3) {
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

			if (categoryId == 3) {
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
				itemsList = mapRow(rs);

			}
			return itemsList;
		}

	}

	public List<ItemDTO> findById(int itemId) throws SQLException {
		String sql = "SELECT name, color, manufacturer, price, stock FROM items where item_id = ?;";
		List<ItemDTO> itemsDetailList = new ArrayList<>();
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, itemId);

			ResultSet rs = ps.executeQuery();

			itemsDetailList = mapRow(rs);

		}
		return itemsDetailList;

	}

	private List<ItemDTO> mapRow(ResultSet rs) throws SQLException {
		List<ItemDTO> List = new ArrayList<>();
		while (rs.next())

		{
			ItemDTO item = new ItemDTO();
			item.setItemId(rs.getInt("item_id"));
			item.setItemName(rs.getString("name"));
			item.setColor(rs.getString("color"));
			item.setManufacturer(rs.getString("manufacturer"));
			item.setPrice(rs.getInt("price"));
			item.setStock(rs.getInt("stock"));
			item.isRecommended();
			List.add(item);
		}
		return List;

	}
}