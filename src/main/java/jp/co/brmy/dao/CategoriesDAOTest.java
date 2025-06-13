package jp.co.brmy.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import jp.co.brmy.dto.CategoriesDTO;
import jp.co.brmy.util.ConnectionUtil;

class CategoriesDAOTest {
	
	
	
	

	@Test
	void testFindAll() {
//		fail("まだ実装されていません");
		
try(Connection conn = ConnectionUtil.getConnectionForJUnit() ){
	
	CategoriesDAO dao = new CategoriesDAO(conn);
	
	List<CategoriesDTO> list = new ArrayList<>();
	list = dao.findAll();
	
	for(CategoriesDTO dto : list) {
		assertEquals(0,dto.getCategory());
		assertEquals("すべて",dto.getName());
		
		break;
	}
	
	
	
}catch(Exception e) {
	fail("まだ実装されていません");
	

}	
}	
	
	
	@Test
	void testFindById() {

	
try(Connection conn = ConnectionUtil.getConnectionForJUnit() ){

CategoriesDAO dao = new CategoriesDAO(conn);


CategoriesDTO categoriesDTO  = new CategoriesDTO();
categoriesDTO = dao.findById(1);

	assertEquals(1,categoriesDTO.getCategory());
	assertEquals("帽子",categoriesDTO.getName());
	
	
categoriesDTO = dao.findById(2);

	assertEquals(2,categoriesDTO.getCategory());
	assertEquals("鞄",categoriesDTO.getName());
	
	

}catch(Exception e) {
	e.printStackTrace();
fail("まだ実装されていません");

	}

}
	}
