package jp.co.brmy.service;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.brmy.dao.BaseDAO;
import jp.co.brmy.dao.ItemsInCartDAO;
import jp.co.brmy.dto.ItemsInCartDTO;
import jp.co.brmy.util.ConnectionUtil;

public class RemoveFromCartCommitTest {

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
	void testRemoveFromCartCommitService() {

		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {
			RemoveFromCartCommitService serv = new RemoveFromCartCommitService();
			ItemsInCartDTO dto = new ItemsInCartDTO();
			ItemsInCartDAO dao = new ItemsInCartDAO(conn);
			dto = serv.removeCart("user", 1);

			assertNotNull(dto);
			assertEquals("user", dto.getUserId());
			assertEquals(1, dto.getItemId());
			assertEquals(2, dto.getAmount());
			String strDate = "2020-10-20";
			java.text.SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date expected = dateFormat.parse(strDate);
			assertEquals(expected, dto.getBookedDate());

			assertEquals("麦わら帽子", dto.getItemsDTO().getName());
			assertEquals("日本帽子製造", dto.getItemsDTO().getManufacturer());
			assertEquals(1, dto.getItemsDTO().getCategoryId());
			assertEquals("黄色", dto.getItemsDTO().getColor());
			assertEquals(4980, dto.getItemsDTO().getPrice());
			assertEquals(12, dto.getItemsDTO().getStock());
			assertEquals(false, dto.getItemsDTO().isRecommended());

			dto = dao.findById("user", 1);
			assertNull(dto);

		} catch (Exception e) {
			e.printStackTrace();
			fail("まだ実装されていません");

		}
	}
}
