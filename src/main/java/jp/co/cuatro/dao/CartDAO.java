package jp.co.cuatro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jp.co.cuatro.dto.CartDTO;
import jp.co.cuatro.dto.ItemDTO;

public class CartDAO {

	private Connection con;

	/**
	 * コンストラクタ
	 * @param con Connectionオブジェクト
	 */
	public CartDAO(Connection con) {
		this.con = con;
	}

	// ユーザーIDによる検索
	public List<CartDTO> findByUserId(String userId) throws SQLException {
		String sql = "select user_id, name, color, manufacturer, price, amount, booked_date, i.item_id from items i inner join items_in_cart ic on i.item_id = ic.item_id WHERE ic.user_id = ?";
		//結果を格納するListオブジェクトを用意
		List<CartDTO> list = new ArrayList<>();
		//データベースの接続と切断
		//SQL実行用オブジェクトの取得
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, userId);
			//SQLの実行と結果オブジェクトの取得
			System.out.println(ps);
			ResultSet rs = ps.executeQuery();
			//結果オブジェクトのnextメソッドを呼び出して戻り値を判定
			while (rs.next()) {
				//戻り値がtrueの場合の処理
				//CartDTOクラスのインスタンスを生成し、変数dtoに代入
				CartDTO cartDto = new CartDTO();
				//カーソルが指す行の各列の値を取得し、
				//変数dtoに設定
				ItemDTO itemDto = new ItemDTO();
				itemDto.setItemName(rs.getString("name"));
				itemDto.setColor(rs.getString("color"));
				itemDto.setManufacturer(rs.getString("manufacturer"));
				itemDto.setPrice(rs.getInt("price"));
				itemDto.setItemId(rs.getInt("item_id"));

				cartDto.setAmount(rs.getInt("amount"));
				cartDto.setItemId(rs.getInt("item_id"));
				cartDto.setUserId(rs.getString("user_id"));
				cartDto.setItem(itemDto);
				cartDto.setBookedDate(rs.getObject("booked_date", LocalDate.class));
				//すべての列の値を設定した変数をListオブジェクトに格納
				list.add(cartDto);
			}
		} catch (SQLException e) {
			System.out.println("実行中にエラーが発生しました");
			e.printStackTrace();
		}
		return list;
	}

	// データの追加
	public int insert(CartDTO cart) throws SQLException {
		String sql = """
				INSERT INTO public.items_in_cart(
					user_id, item_id, amount, booked_date)
					VALUES (?, ?, ?, ?)""";
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, cart.getUserId());
			ps.setInt(2, cart.getItemId());
			ps.setInt(3, cart.getAmount());
			ps.setObject(4, cart.getBookedDate());

			return ps.executeUpdate();
		}
	}

	// データの更新
	public int update(CartDTO cart) throws SQLException {
		String sql = """
				UPDATE public.items_in_cart
					SET amount=?
				 WHERE user_id = ? AND item_id = ?""";
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, cart.getAmount());
			ps.setString(2, cart.getUserId());
			ps.setInt(3, cart.getItemId());

			return ps.executeUpdate();
		}
	}

	// 特定のユーザーが、特定の商品をすでにカートに入れているか検索する
	public CartDTO findByUserAndItem(String userId, int itemId) throws SQLException {
		String sql = "SELECT c.user_id, c.item_id, c.amount, c.booked_date, i.name, i.manufacturer, i.price\n"
				+ "FROM public.items_in_cart c \n"
				+ "INNER JOIN public.items i ON c.item_id = i.item_id \n"
				+ "WHERE c.user_id = ? AND c.item_id = ?";
		;

		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, userId);
			ps.setInt(2, itemId);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					// すでにデータが存在した場合は、中身を詰めたDTOを返す
					CartDTO cartDto = new CartDTO();
					ItemDTO itemDto = new ItemDTO();

					itemDto.setItemName(rs.getString("name"));
					itemDto.setManufacturer(rs.getString("manufacturer"));
					itemDto.setPrice(rs.getInt("price"));
					itemDto.setItemId(rs.getInt("item_id"));

					cartDto.setUserId(rs.getString("user_id"));
					cartDto.setItemId(rs.getInt("item_id"));
					cartDto.setAmount(rs.getInt("amount"));
					cartDto.setBookedDate(rs.getObject("booked_date", LocalDate.class));
					cartDto.setItem(itemDto);

					return cartDto;
				}
			}
		}
		return null;
	}

	// データの削除
	public int delete(CartDTO cart) throws SQLException {
		String sql = "DELETE FROM public.items_in_cart\n"
				+ "	WHERE user_id = ? AND item_id = ?";
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, cart.getUserId());
			ps.setInt(2, cart.getItemId());

			return ps.executeUpdate();
		}
	}

	public void deleteCartItem(Connection conn, String userId, int itemId) throws SQLException {

		// user_id と item_id の両方が一致するレコードだけを削除する
		String sql = "DELETE FROM items_in_cart WHERE user_id = ? AND item_id = ?";

		try (PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, userId); // ユーザーID
			ps.setInt(2, itemId); // 商品ID

			ps.executeUpdate();
		}
	}
}
