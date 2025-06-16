package jp.co.brmy.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.brmy.dto.ItemsDTO;
import jp.co.brmy.util.ConnectionUtil;

class ItemsDAOTest {

	@BeforeEach
	void init() {
		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {
			conn.setAutoCommit(false);
			ItemsInCartDAO dao = new ItemsInCartDAO(conn);
			try {
				dao.insertBatch("sqlFiles/init.sql");

				conn.commit();
			} catch (Exception e) {
				conn.rollback();
				throw e;
			}
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	@Test
	void testFindAll() {
		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {
			ItemsDAO dao = new ItemsDAO(conn);
			List<ItemsDTO> list = new ArrayList<>();
			list = dao.findAll();
			for (ItemsDTO dto : list) {
				assertEquals(1, dto.getItemId());
				assertEquals("麦わら帽子", dto.getName());
				assertEquals("日本帽子製造", dto.getManufacturer());
				assertEquals(1, dto.getCategoryId());
				assertEquals("黄色", dto.getColor());
				assertEquals(4980, dto.getPrice());
				assertEquals(12, dto.getStock());
				assertEquals(false, dto.isRecommended());

				break;

			}

		} catch (Exception e) {
			e.printStackTrace();
			fail("まだ実装されていません");

		}
	}

	@Test
	void testFindById() {
		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {
			ItemsDAO dao = new ItemsDAO(conn);
			ItemsDTO item = dao.findById(1);
			assertNotNull(item);
			assertEquals(1, item.getItemId());
			assertEquals("麦わら帽子", item.getName());
			assertEquals("日本帽子製造", item.getManufacturer());
			assertEquals(1, item.getCategoryId());
			assertEquals("黄色", item.getColor());
			assertEquals(4980, item.getPrice());
			assertEquals(12, item.getStock());
			assertEquals(false, item.isRecommended());

			item = dao.findById(99);
			assertNull(item);

		} catch (Exception e) {
			e.printStackTrace();
			fail("まだ実装されていません");

		}
	}

	@Test
	void testFindNameSearch() {
		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {
			ItemsDAO dao = new ItemsDAO(conn);

			List<ItemsDTO> list = new ArrayList<>();
			list = dao.findNameSearch("麦わら");
			assertEquals(2, list.size());
			for (ItemsDTO dto : list) {
				assertEquals(1, dto.getItemId());
				assertEquals("麦わら帽子", dto.getName());
				assertEquals("日本帽子製造", dto.getManufacturer());
				assertEquals(1, dto.getCategoryId());
				assertEquals("黄色", dto.getColor());
				assertEquals(4980, dto.getPrice());
				assertEquals(12, dto.getStock());
				assertEquals(false, dto.isRecommended());

				break;

			}

			list = dao.findNameSearch(1);
			assertEquals(11, list.size());
			for (ItemsDTO dto : list) {
				assertEquals(1, dto.getItemId());
				assertEquals("麦わら帽子", dto.getName());
				assertEquals("日本帽子製造", dto.getManufacturer());
				assertEquals(1, dto.getCategoryId());
				assertEquals("黄色", dto.getColor());
				assertEquals(4980, dto.getPrice());
				assertEquals(12, dto.getStock());
				assertEquals(false, dto.isRecommended());

				break;

			}

			list = dao.findNameSearch("麦わら", 1);
			assertEquals(2, list.size());
			for (ItemsDTO dto : list) {
				assertEquals(1, dto.getItemId());
				assertEquals("麦わら帽子", dto.getName());
				assertEquals("日本帽子製造", dto.getManufacturer());
				assertEquals(1, dto.getCategoryId());
				assertEquals("黄色", dto.getColor());
				assertEquals(4980, dto.getPrice());
				assertEquals(12, dto.getStock());
				assertEquals(false, dto.isRecommended());

				break;

			}

		} catch (Exception e) {
			e.printStackTrace();
			fail("まだ実装されていません");

		}
	}

	@Test
	void testFindNameSearchNull() {
		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {
			ItemsDAO dao = new ItemsDAO(conn);

			List<ItemsDTO> list = new ArrayList<>();
			list = dao.findNameSearch("aaaa");
			assertNotNull(list);
			assertEquals(0, list.size());

		} catch (Exception e) {
			e.printStackTrace();
			fail("まだ実装されていません");

		}
	}

	@Test
	void testUpdateStock() {
		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {
			ItemsDAO dao = new ItemsDAO(conn);
			dao.updateStock(10, 1);

			ItemsDTO item = dao.findById(1);
			assertEquals(10, item.getStock());

		} catch (Exception e) {
			e.printStackTrace();
			fail("まだ実装されていません");

		}
	}
}
