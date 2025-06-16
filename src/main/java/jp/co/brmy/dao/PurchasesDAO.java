package jp.co.brmy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import jp.co.brmy.dto.ItemsDTO;
import jp.co.brmy.dto.PurchaseDetailsDTO;
import jp.co.brmy.dto.PurchasesDTO;

public class PurchasesDAO extends BaseDAO {

	public PurchasesDAO(Connection conn) {
		super(conn);

	}

	public int insert(PurchasesDTO dto) throws SQLException {
		String sql = "INSERT INTO purchases(purchased_user,purchased_date,destination)VALUES(?,?,?)";
		int result;

		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, dto.getPurchaseUser());
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String formattedDate = simpleDateFormat.format(dto.getPurchaseDate());
			ps.setDate(2, java.sql.Date.valueOf(formattedDate));
			ps.setString(3, dto.getDestination());

			result = ps.executeUpdate();

			return result;

		}

	}

	public int update(int num) throws SQLException {
		String sql = "update purchases set cancel=true where purchase_id=?";
		int result;

		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, num);
			result = ps.executeUpdate();

			return result;

		}

	}

	public PurchasesDTO findById(int purchaseId) throws SQLException {

		String sql = "select purchases.purchase_id,purchases.purchased_user, purchases.purchased_date,purchases.destination,purchases.cancel , purchase_details.purchase_detail_id,purchase_details.item_id,purchase_details.amount, items.name,items.color,items.manufacturer,items.price,items.stock from  purchases inner join purchase_details on  purchases.purchase_id = purchase_details.purchase_id inner join items on purchase_details.item_id =items.item_id where purchases.purchase_id=?";

		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			System.out.println(ps);
			ps.setInt(1, purchaseId);
			PurchasesDTO purchases = null;
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				purchases = new PurchasesDTO();
				purchases.setPurchaseId(rs.getInt("purchase_id"));
				purchases.setPurchaseUser(rs.getString("purchased_user"));
				purchases.setPurchaseDate(rs.getDate("purchased_date"));
				purchases.setDestination(rs.getString("destination"));
				purchases.setCansel(rs.getBoolean("cancel"));
				List<PurchaseDetailsDTO> detaillist = new ArrayList<>();

				while (rs.next()) {
					if (purchases.getPurchaseId() == rs.getInt("purchase_id")) {

						ItemsDTO item = new ItemsDTO();
						PurchaseDetailsDTO details = new PurchaseDetailsDTO();
						item.setColor(rs.getString("color"));
						item.setManufacturer(rs.getString("manufacturer"));
						item.setName(rs.getString("name"));
						item.setPrice(rs.getInt("price"));
						item.setStock(rs.getInt("stock"));

						details.setItemsDTO(item);

						details.setAmount(rs.getInt("amount"));
						detaillist.add(details);

						rs.next();//超えた先を参照している

					} else {
						break;
					}

				}
			}

			return purchases;

		}

	}

	public List<PurchasesDTO> findAll() throws SQLException {

		String sql = "select purchases.purchase_id,purchases.purchased_user, purchases.purchased_date,purchases.destination,purchases.cancel , purchase_details.purchase_detail_id,purchase_details.item_id,purchase_details.amount, items.name,items.color,items.manufacturer,items.price,items.stock from  purchases inner join purchase_details on  purchases.purchase_id = purchase_details.purchase_id inner join items on purchase_details.item_id =items.item_id";

		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			System.out.println(ps);
			List<PurchasesDTO> list = new ArrayList<PurchasesDTO>();
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				PurchasesDTO purchases = new PurchasesDTO();
				purchases.setPurchaseId(rs.getInt("purchase_id"));
				purchases.setPurchaseUser(rs.getString("purchased_user"));
				purchases.setPurchaseDate(rs.getDate("purchased_date"));
				purchases.setDestination(rs.getString("destination"));
				purchases.setCansel(rs.getBoolean("cancel"));
				List<PurchaseDetailsDTO> detaillist = new ArrayList<>();
				do {

					ItemsDTO item = new ItemsDTO();
					PurchaseDetailsDTO details = new PurchaseDetailsDTO();
					item.setColor(rs.getString("color"));
					item.setManufacturer(rs.getString("manufacturer"));
					item.setName(rs.getString("name"));
					item.setPrice(rs.getInt("price"));
					item.setStock(rs.getInt("stock"));

					details.setItemsDTO(item);

					details.setAmount(rs.getInt("amount"));
					detaillist.add(details);

					rs.next();

				} while (purchases.getPurchaseId() == rs.getInt("purchase_id"));
				purchases.setPrchaseDetailsDTO(detaillist);
				list.add(purchases);

			}

			return list;

		}

	}
}
