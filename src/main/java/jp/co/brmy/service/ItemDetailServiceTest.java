package jp.co.brmy.service;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.brmy.dao.BaseDAO;
import jp.co.brmy.dto.ItemsDTO;
import jp.co.brmy.util.ConnectionUtil;

class ItemDetailServiceTest {

	@BeforeEach
	void init() {
		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {
			ConnectionUtil.mode = ConnectionUtil.MODE.TEST;

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
	void testFindId() throws SQLException, ServletException {
		//		fail("まだ実装されていません");
		ItemDetailService detail = new ItemDetailService();
		ItemsDTO dto = detail.findById(1);

		assertEquals(1, dto.getItemId());
		assertEquals("麦わら帽子", dto.getName());
		assertEquals("日本帽子製造", dto.getManufacturer());
		assertEquals(1, dto.getCategoryId());
		assertEquals("黄色", dto.getColor());
		assertEquals(4980, dto.getPrice());
		assertEquals(12, dto.getStock());
		assertEquals(false, dto.isRecommended());

		assertEquals("帽子", dto.getCategoriesDTO().getName());
		assertEquals(1, dto.getCategoriesDTO().getCategoryId());

	}

}
