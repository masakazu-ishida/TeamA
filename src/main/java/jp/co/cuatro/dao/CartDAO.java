package jp.co.cuatro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.cuatro.dto.CartDTO;

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
	public List<CartDTO> findAll() throws SQLException {
		//String sql = "SELECT name, color, manufacturer, price, amount, item_id FROM public.users";
		String sql = "select name, color, manufacturer, price, amount, i.item_id from items i inner join items_in_cart ic on i.item_id = ic.item_id";
		//結果を格納するListオブジェクトを用意
		List<CartDTO> list = new ArrayList<>();
		//データベースの接続と切断
		//SQL実行用オブジェクトの取得
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			//SQLの実行と結果オブジェクトの取得
			ResultSet rs = ps.executeQuery();
			//結果オブジェクトのnextメソッドを呼び出して戻り値を判定
			while (rs.next()) {
				//戻り値がtrueの場合の処理
				//CartDTOクラスのインスタンスを生成し、変数dtoに代入
				CartDTO dto = new CartDTO();
				//カーソルが指す行の各列の値を取得し、
				//変数dtoに設定
				dto.setName(rs.getString("name"));
				dto.setColor(rs.getString("color"));
				dto.setManufacturer(rs.getString("manufacturer"));
				dto.setPrice(rs.getInt("price"));
				dto.setAmount(rs.getInt("amount"));
				dto.setItemId(rs.getString("item_id"));
				//すべての列の値を設定した変数をListオブジェクトに格納
				list.add(dto);
			}
		} catch (SQLException e) {
			System.out.println("実行中にエラーが発生しました");
			e.printStackTrace();
		}
		return list;
	}
}