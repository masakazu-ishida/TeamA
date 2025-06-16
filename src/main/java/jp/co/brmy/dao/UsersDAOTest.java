package jp.co.brmy.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import jp.co.brmy.dto.UsersDTO;
import jp.co.brmy.util.ConnectionUtil;

class UsersDAOTest {

	@Test
	void testFindId() {
//		fail("まだ実装されていません");
		
		try (Connection conn = ConnectionUtil.getConnectionForJUnit()){
			
			UsersDAO dao = new UsersDAO(conn);
			
			UsersDTO dto = dao.findById("user");
			assertNotNull(dto);
            assertEquals("user", dto.getUserId());
            assertEquals("userpass", dto.getPassword());
            assertEquals("鳥取太郎", dto.getName());
            assertEquals("東京都港区赤坂3-3-3", dto.getAddress());
            
            dto = dao.findById("aaaa");
            assertNull(dto);
            
            
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			fail("まだ実装されていません");
		}
		
	}









	@Test
	void testFindAll() {
//		fail("まだ実装されていません");
		
		try (Connection conn = ConnectionUtil.getConnectionForJUnit()){
			
			UsersDAO dao = new UsersDAO(conn);
			
			List<UsersDTO> list = new ArrayList<>();
			list = dao.findAll();
			assertEquals(1, list.size());
			for (UsersDTO dto : list) {

	            assertEquals("user", dto.getUserId());
	            assertEquals("userpass", dto.getPassword());
	            assertEquals("鳥取太郎", dto.getName());
	            assertEquals("東京都港区赤坂3-3-3", dto.getAddress());
	            break;
	            
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			fail("まだ実装されていません");
		}
		
	}

}
