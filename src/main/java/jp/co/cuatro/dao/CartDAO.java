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

	// 全件検索 
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

				cartDto.setAmount(rs.getInt("amount"));
				cartDto.setItemId(rs.getString("item_id"));
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
}