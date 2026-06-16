package jp.co.cuatro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.co.cuatro.dto.UsersDTO;

public class UsersDAO {

	private Connection con;

	public UsersDAO(Connection con) {
		this.con = con;
	}

	// 主キーによる検索
	public UsersDTO findById(String userId) throws SQLException {
		String sql = "SELECT user_id, password, name, address FROM public.users WHERE user_id = ?";
		UsersDTO user = null;
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, userId);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {

					//mapRowはResultSetからDTOへの変換メソッド。複数箇所で利用するので共通化
					user = mapRow(rs);
					return user;
				}
			}
		}
		return null; // 見つからない場合はnullを返す
	}

	// ResultSetからエンティティへのマッピングを行う共通メソッド
	//主キー検索と全件検索で重複を防ぐため
	private UsersDTO mapRow(ResultSet rs) throws SQLException {
		UsersDTO user = new UsersDTO();
		user.setUserId(rs.getString("user_id"));
		user.setPassword(rs.getString("password"));
		user.setName(rs.getString("name"));
		user.setAddress(rs.getString("address"));
		return user;
	}
}
