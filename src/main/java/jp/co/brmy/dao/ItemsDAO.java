package jp.co.brmy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.brmy.dto.CategoriesDTO;
import jp.co.brmy.dto.ItemsDTO;

public class ItemsDAO extends BaseDAO {

	public ItemsDAO(Connection conn) {
		// TODO 自動生成されたコンストラクター・スタブ
		super(conn);
	}

	public List<ItemsDTO> findAll() throws SQLException {
		String sql = "select item_id,items.name,manufacturer,items.category_id,color,price,stock,recommended,categories.name as category_name\n"
				+ "from items inner join categories on items.category_id=categories.category_id   order by item_id";
		List<ItemsDTO> itemlist = new ArrayList<>();
		try (PreparedStatement ps = conn.prepareStatement(sql)) {

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				ItemsDTO item = new ItemsDTO();

				item.setCategoryId(rs.getInt("category_id"));
				item.setColor(rs.getString("color"));
				item.setItemId(rs.getInt("item_id"));
				item.setManufacturer(rs.getString("manufacturer"));
				item.setName(rs.getString("name"));
				item.setPrice(rs.getInt("price"));
				item.setRecommended(rs.getBoolean("recommended"));
				item.setStock(rs.getInt("stock"));

				CategoriesDTO cat = new CategoriesDTO();
				cat.setCategoryId(rs.getInt("category_id"));
				cat.setName(rs.getString("category_name"));

				item.setCategoriesDTO(cat);

				itemlist.add(item);

			}

		}
		return itemlist;

	}

	public List<ItemsDTO> findAllByLimit(int pageNumber) throws SQLException {
		String sql = "select item_id,items.name,manufacturer,items.category_id,color,price,stock,recommended,categories.name as category_name\n"
				+ "from items inner join categories on items.category_id=categories.category_id order by item_id offset ? limit 10";
		List<ItemsDTO> itemlist = new ArrayList<>();
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, pageNumber);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				ItemsDTO item = new ItemsDTO();

				item.setCategoryId(rs.getInt("category_id"));
				item.setColor(rs.getString("color"));
				item.setItemId(rs.getInt("item_id"));
				item.setManufacturer(rs.getString("manufacturer"));
				item.setName(rs.getString("name"));
				item.setPrice(rs.getInt("price"));
				item.setRecommended(rs.getBoolean("recommended"));
				item.setStock(rs.getInt("stock"));

				CategoriesDTO cat = new CategoriesDTO();
				cat.setCategoryId(rs.getInt("category_id"));
				cat.setName(rs.getString("category_name"));

				item.setCategoriesDTO(cat);

				itemlist.add(item);

			}

		}
		return itemlist;

	}

	public ItemsDTO findById(int id) throws SQLException {
		String sql = "select item_id,items.name,manufacturer,items.category_id,color,price,stock,recommended,categories.name as category_name from items inner join categories on items.category_id=categories.category_id where item_id=?  order by item_id";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			ItemsDTO item = null;

			if (rs.next()) {
				item = new ItemsDTO();
				item.setCategoryId(rs.getInt("category_id"));
				item.setColor(rs.getString("color"));
				item.setItemId(rs.getInt("item_id"));
				item.setManufacturer(rs.getString("manufacturer"));
				item.setName(rs.getString("name"));
				item.setPrice(rs.getInt("price"));
				item.setRecommended(rs.getBoolean("recommended"));
				item.setStock(rs.getInt("stock"));

				CategoriesDTO cat = new CategoriesDTO();
				cat.setCategoryId(rs.getInt("category_id"));
				cat.setName(rs.getString("category_name"));

				item.setCategoriesDTO(cat);
			}
			return item;

		}

	}

	public List<ItemsDTO> findNameSearch(String name) throws SQLException {
		String sql = "select item_id,items.name,manufacturer,items.category_id,color,price,stock,recommended,categories.name as category_name from items inner join categories on items.category_id=categories.category_id where items.name like ?  order by item_id";
		List<ItemsDTO> itemlist = new ArrayList<>();
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			name = "%" + name + "%";
			ps.setString(1, name);
			System.out.println(ps);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				ItemsDTO item = new ItemsDTO();

				item.setCategoryId(rs.getInt("category_id"));
				item.setColor(rs.getString("color"));
				item.setItemId(rs.getInt("item_id"));
				item.setManufacturer(rs.getString("manufacturer"));
				item.setName(rs.getString("name"));
				item.setPrice(rs.getInt("price"));
				item.setRecommended(rs.getBoolean("recommended"));
				item.setStock(rs.getInt("stock"));

				CategoriesDTO cat = new CategoriesDTO();
				cat.setCategoryId(rs.getInt("category_id"));
				cat.setName(rs.getString("category_name"));

				item.setCategoriesDTO(cat);

				itemlist.add(item);

			}

		}
		return itemlist;

	}

	public List<ItemsDTO> findNameSearch(int id) throws SQLException {
		String sql = "select item_id,items.name,manufacturer,items.category_id,color,price,stock,recommended,categories.name as category_name from items inner join categories on items.category_id=categories.category_id where items.category_id= ?  order by item_id";
		List<ItemsDTO> itemlist = new ArrayList<>();
		try (PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, id);
			System.out.println(ps);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				ItemsDTO item = new ItemsDTO();

				item.setCategoryId(rs.getInt("category_id"));
				item.setColor(rs.getString("color"));
				item.setItemId(rs.getInt("item_id"));
				item.setManufacturer(rs.getString("manufacturer"));
				item.setName(rs.getString("name"));
				item.setPrice(rs.getInt("price"));
				item.setRecommended(rs.getBoolean("recommended"));
				item.setStock(rs.getInt("stock"));

				CategoriesDTO cat = new CategoriesDTO();
				cat.setCategoryId(rs.getInt("category_id"));
				cat.setName(rs.getString("category_name"));

				item.setCategoriesDTO(cat);

				itemlist.add(item);

			}

		}
		return itemlist;

	}

	public List<ItemsDTO> findNameSearch(String name, int id) throws SQLException {
		String sql = "select item_id,items.name,manufacturer,items.category_id,color,price,stock,recommended,categories.name as category_name from items inner join categories on items.category_id=categories.category_id where items.name like ?  and items.category_id=? order by item_id";
		List<ItemsDTO> itemlist = new ArrayList<>();
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			name = "%" + name + "%";
			ps.setString(1, name);
			ps.setInt(2, id);
			System.out.println(ps);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				ItemsDTO item = new ItemsDTO();

				item.setCategoryId(rs.getInt("category_id"));
				item.setColor(rs.getString("color"));
				item.setItemId(rs.getInt("item_id"));
				item.setManufacturer(rs.getString("manufacturer"));
				item.setName(rs.getString("name"));
				item.setPrice(rs.getInt("price"));
				item.setRecommended(rs.getBoolean("recommended"));
				item.setStock(rs.getInt("stock"));

				CategoriesDTO cat = new CategoriesDTO();
				cat.setCategoryId(rs.getInt("category_id"));
				cat.setName(rs.getString("category_name"));
				item.setCategoriesDTO(cat);

				itemlist.add(item);

			}

		}
		return itemlist;

	}

	public int updateStock(int stock, int id) throws SQLException {
		String sql = "update items set stock=? where item_id=?";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, stock);
			ps.setInt(2, id);
			System.out.println(ps);
			int a = ps.executeUpdate();
			return a;

		}

	}
}
