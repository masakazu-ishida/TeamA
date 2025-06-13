package jp.co.brmy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.brmy.dto.CategoriesDTO;

public class CategoriesDAO extends BaseDAO{
	 
		

		
		public CategoriesDAO(Connection conn) {
			super(conn);
			
		}
	
		
		

	public List<CategoriesDTO> findAll() throws SQLException {

	
		
		String sql1 = "SELECT category_id,name FROM categories ORDER BY category_id";
			List<CategoriesDTO> categoriesList = new ArrayList<>() ;
	
		try(PreparedStatement ps = conn.prepareStatement(sql1)){
			System.out.println(ps);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
		CategoriesDTO categories = new CategoriesDTO();
		categories.setCategoryId(rs.getInt("category_id"));
		categories.setName(rs.getString("name"));
		categoriesList.add(categories);
				
			}
			
			return categoriesList;
	
	}
		
}


		
	public CategoriesDTO findById(int categoryId) throws SQLException{
			
	
	
	String sql2 = "SELECT  category_id,name FROM categories WHERE category_id = ? ORDER BY category_id";
			CategoriesDTO categoriesDto = null;
	
	try (PreparedStatement ps = conn.prepareStatement(sql2)) {
		System.out.println(ps);
		
		ps.setInt(1,categoryId );
		
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			categoriesDto = new CategoriesDTO();
			categoriesDto.setCategoryId(rs.getInt("category_id"));
			categoriesDto.setName(rs.getString("name"));
			
			
		}
	
		return categoriesDto;
		
		}
	}
}
			
