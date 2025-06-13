package jp.co.brmy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.co.brmy.dto.AdministratorsDTO;

public class AdministratorsLoginDAO extends BaseDAO {
	
	public AdministratorsLoginDAO(Connection conn) {
		super(conn);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public AdministratorsDTO findById(String adminId,String pass) throws SQLException{
		String sql ="SELECT admin_id,password, name from administrators where admin_id = ? and password = ?";
		AdministratorsDTO adminDto = null;
		
		try (PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setString(1, adminId);
			ps.setString(2, pass);
			
			System.out.println(ps);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				adminDto = new AdministratorsDTO();
				adminDto.setAdminId(rs.getString("admin_id"));
				adminDto.setPassword(rs.getString("password"));
			}	
		}return adminDto;
	}
}

