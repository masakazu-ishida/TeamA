package jp.co.brmy.service;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.brmy.dao.BaseDAO;
import jp.co.brmy.dto.ItemsDTO;
import jp.co.brmy.util.ConnectionUtil;

class ItemsServiceTest {
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
	void testFindName() throws SQLException, ServletException {
		//		fail("まだ実装されていません");

		ItemsService itemsService = new ItemsService();
		List<ItemsDTO> itemsDto = itemsService.findNameSearch("麦わら", 0);

		for (ItemsDTO dto : itemsDto) {
			assertEquals(1, dto.getItemId());
			assertEquals("麦わら帽子", dto.getName());
			assertEquals("日本帽子製造", dto.getManufacturer());
			assertEquals(1, dto.getCategoryId());
			assertEquals("黄色", dto.getColor());
			assertEquals(4980, dto.getPrice());
			assertEquals(12, dto.getStock());
			assertEquals(false, dto.isRecommended());

			assertEquals(2, itemsDto.size());

			//持田さん完成の暁に行うテスト
			assertEquals("帽子", dto.getCategoriesDTO().getName());
			assertEquals(1, dto.getCategoriesDTO().getCategoryId());
			break;

		}
	}

	@Test
	void testFindId() throws SQLException, ServletException {
		//		fail("まだ実装されていません");

		ItemsService itemsService = new ItemsService();
		List<ItemsDTO> itemsDto = itemsService.findNameSearch("", 2);

		for (ItemsDTO dto : itemsDto) {
			assertEquals(12, dto.getItemId());
			assertEquals("鞄A", dto.getName());
			assertEquals("東京鞄店", dto.getManufacturer());
			assertEquals(2, dto.getCategoryId());
			assertEquals("青色", dto.getColor());
			assertEquals(1980, dto.getPrice());
			assertEquals(18, dto.getStock());
			assertEquals(true, dto.isRecommended());

			assertEquals(9, itemsDto.size());

			assertEquals("鞄", dto.getCategoriesDTO().getName());
			assertEquals(2, dto.getCategoriesDTO().getCategoryId());
			break;

		}
	}

	@Test
	void testFindNameId() throws SQLException, ServletException {
		//		fail("まだ実装されていません");
		ItemsService itemsService = new ItemsService();
		List<ItemsDTO> itemsDto = itemsService.findNameSearch("麦わら", 1);

		for (ItemsDTO dto : itemsDto) {
			assertEquals(1, dto.getItemId());
			assertEquals("麦わら帽子", dto.getName());
			assertEquals("日本帽子製造", dto.getManufacturer());
			assertEquals(1, dto.getCategoryId());
			assertEquals("黄色", dto.getColor());
			assertEquals(4980, dto.getPrice());
			assertEquals(12, dto.getStock());
			assertEquals(false, dto.isRecommended());

			assertEquals(2, itemsDto.size());

			assertEquals("帽子", dto.getCategoriesDTO().getName());
			assertEquals(1, dto.getCategoriesDTO().getCategoryId());
			break;

		}

	}

	@Test
	void testFindAll() throws SQLException, ServletException {
		//		fail("まだ実装されていません");
		ItemsService itemsService = new ItemsService();
		List<ItemsDTO> itemsDto = itemsService.findNameSearch("", 0);

		for (ItemsDTO dto : itemsDto) {
			assertEquals(1, dto.getItemId());
			assertEquals("麦わら帽子", dto.getName());
			assertEquals("日本帽子製造", dto.getManufacturer());
			assertEquals(1, dto.getCategoryId());
			assertEquals("黄色", dto.getColor());
			assertEquals(4980, dto.getPrice());
			assertEquals(12, dto.getStock());
			assertEquals(false, dto.isRecommended());

			assertEquals(20, itemsDto.size());

			assertEquals("帽子", dto.getCategoriesDTO().getName());
			assertEquals(1, dto.getCategoriesDTO().getCategoryId());
			break;

		}

	}
}
