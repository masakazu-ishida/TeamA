package jp.co.brmy.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.brmy.dto.CategoriesDTO;
import jp.co.brmy.util.ConnectionUtil;

class CategoriesDAOTest {
	
	@BeforeEach
    void init() {
        try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {
            
            BaseDAO dao = new BaseDAO(conn);
            try {
                dao.insertBatch("sqlFiles/init.sql");
    
            } catch (Exception e) {
                throw e;
            }
        } catch (Exception e) {
            fail(e.getMessage());
        }    
    }
	
	

	@Test
	void testFindAll() {
//		fail("まだ実装されていません");
		
try(Connection conn = ConnectionUtil.getConnectionForJUnit() ){
	
	CategoriesDAO dao = new CategoriesDAO(conn);
	
	List<CategoriesDTO> list = new ArrayList<>();
	list = dao.findAll();
	
	assertEquals(3, list.size());
	

	for(CategoriesDTO dto : list) {
		assertEquals(0,dto.getCategoryId());
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
assertNotNull(categoriesDTO);


	assertEquals(1,categoriesDTO.getCategoryId());
	assertEquals("帽子",categoriesDTO.getName());
	
	
categoriesDTO = dao.findById(2);
assertNotNull(categoriesDTO);

	assertEquals(2,categoriesDTO.getCategoryId());
	assertEquals("鞄",categoriesDTO.getName());
	
	
	
categoriesDTO = dao.findById(4);
assertNull(categoriesDTO);


	

}catch(Exception e) {
	e.printStackTrace();
fail("まだ実装されていません");

	}

}
	}
