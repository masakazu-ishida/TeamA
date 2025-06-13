package jp.co.brmy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.co.brmy.dto.PurchaseDetailsDTO;

public class PurchaseDetailsDAO extends BaseDAO {

	public PurchaseDetailsDAO(Connection conn) {
		super(conn);
		// TODO 自動生成されたコンストラクター・スタブ
	}
	
	 public int insert(PurchaseDetailsDTO dto) throws SQLException{
		 String sql = "INSERT INTO purchase_details (purchase_id,item_id,amount) VALUES(?,?,?)";
		 try( PreparedStatement ps = conn.prepareStatement(sql)){
			 ps.setInt(1, dto.getPurchaseId());
			 ps.setInt(2, dto.getItemId());
			 ps.setInt(3, dto.getAmount());
			 
			 System.out.println(ps);
			 int a= ps.executeUpdate();
		 
		 return a;
		 }
	 }
	
	
	
	public PurchaseDetailsDTO findById(int purchaseDetailId) throws SQLException{
		String sql ="SELECT purchase_detail_id,purchase_id,item_id,amount"
				+ " from purchase_details where purchase_detail_id = ?";
		PurchaseDetailsDTO detailsDto = null;
		
		try (PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setInt(1, purchaseDetailId);
			
			
			System.out.println(ps);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				detailsDto = new PurchaseDetailsDTO();
				detailsDto.setPurchaseDetailId(purchaseDetailId);
			}	
		}return detailsDto;
	}
	
	

}
