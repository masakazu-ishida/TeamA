package jp.co.brmy.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.co.brmy.dto.PurchasesDTO;

public class PurchasesDAO extends BaseDAO {
	 
	

	
		public PurchasesDAO(Connection conn) {
			super(conn);
			
		}
		
		
		public int insert(PurchasesDTO dto)throws SQLException {
			String sql1 = "INSERT INTO purchases(purchase_id,purchased_user,purchased_date,destination,cancel)VALUES(?,?,?,?,?)";
			int result = 0;
			
			try (PreparedStatement ps = conn.prepareStatement(sql1)) {
				ps.setInt(1, dto.getPurchaseId());
				ps.setString(2, dto.getPurchaseUser());
				ps.setDate(3, dto.getPurchaseDate());
				ps.setString(4, dto.getDestination());
				ps.setBoolean(5, dto.getCansel());
				

				result = ps.executeUpdate();
				
			

			return result;
			
	
			
		}
		
		
		
}
		
		
	public int update(PurchasesDTO purchasesDto) throws SQLException {
		String sql2 = "UPDATE purchases SET cansel = ? WHERE purchase_id = ?";
		int result = 0;
			
		try (PreparedStatement ps = conn.prepareStatement(sql2)) {
			ps.setBoolean(1, purchasesDto.getCansel());
			ps.setInt(2,purchasesDto.getPurchaseId());				
			result = ps.executeUpdate();
				
				
			
			
			return result;
		
			
		}
		
	
		}
		
		

	public PurchasesDTO findById(int purchaseId) throws SQLException {


		String sql3 = "SELECT purchase_id,purchased_user,purchased_date,destination,cancel FROM purchases WHERE purchase_id = ? ORDER BY purchase_id";
		 PurchasesDTO purchases = null;
	
		try(PreparedStatement ps = conn.prepareStatement(sql3)){
			System.out.println(ps);
			ps.setInt(1,purchaseId);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				purchases = new PurchasesDTO();
				purchases.setPurchaseId(rs.getInt("purchase_id"));
				purchases.setPurchaseUser(rs.getString("purchased_user"));
				purchases.setPurchaseDate(rs.getDate("purchased_date"));
				purchases.setDestination(rs.getString("destination"));
				purchases.setCansel(rs.getBoolean("cancel"));
		
		
		
			}
			return purchases;
			
			
		}
	
	}
	}
	


