package jp.co.cuatro.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.cuatro.dto.ItemDTO;
import jp.co.cuatro.util.ConnectionUtil;
import jp.co.cuatro.util.TestBase;

class ItemDAOTest extends TestBase {

	@BeforeEach
	public void setUp() throws Exception {
		String sqlFilePath = "/init_data.sql";
		super.initSQLFiles(sqlFilePath);
	}

	@AfterAll
	public static void cleanUp() {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Test
	void testFindByCondition() {
		try (Connection conn = ConnectionUtil.getConnection(null)) {
			ItemDAO dao = new ItemDAO(conn);
			for (int categoryId = 1; categoryId < 4; categoryId++) {
				if (categoryId == 1) {
					String name1 = "";
					List<ItemDTO> itemsList = dao.findByCondition(categoryId, name1);
					assertNotNull(itemsList);
					assertEquals(11, itemsList.size());
					ItemDTO item = itemsList.get(0);
					assertEquals(1, item.getItemId());
					assertEquals("麦わら帽子", item.getItemName());
					assertEquals("黄色", item.getColor());
					assertEquals("日本帽子製造", item.getManufacturer());
					assertEquals(4980, item.getPrice());

					String name2 = "ストロー";
					itemsList = dao.findByCondition(categoryId, name2);
					assertNotNull(itemsList);
					assertEquals(3, itemsList.size());
					item = itemsList.get(0);
					assertEquals(2, item.getItemId());
					assertEquals("ストローハット", item.getItemName());
					assertEquals("茶色", item.getColor());
					assertEquals("(株)ストローハットジャパン", item.getManufacturer());
					assertEquals(3480, item.getPrice());

					String name3 = "aaaa";
					itemsList = dao.findByCondition(categoryId, name3);
					assertNotNull(itemsList);
					assertEquals(0, itemsList.size());
				}
				if (categoryId == 2) {
					String name1 = "";
					List<ItemDTO> itemsList = dao.findByCondition(categoryId, name1);
					assertNotNull(itemsList);
					assertEquals(9, itemsList.size());
					ItemDTO item = itemsList.get(0);
					assertEquals(12, item.getItemId());
					assertEquals("鞄A", item.getItemName());
					assertEquals("青色", item.getColor());
					assertEquals("東京鞄店", item.getManufacturer());
					assertEquals(1980, item.getPrice());

					String name2 = "H";
					itemsList = dao.findByCondition(categoryId, name2);
					assertNotNull(itemsList);
					assertEquals(1, itemsList.size());
					item = itemsList.get(0);
					assertEquals(16, item.getItemId());
					assertEquals("鞄H", item.getItemName());
					assertEquals("茶色", item.getColor());
					assertEquals("日本鞄製造", item.getManufacturer());
					assertEquals(780, item.getPrice());

					String name3 = "aaaa";
					itemsList = dao.findByCondition(categoryId, name3);
					assertNotNull(itemsList);
					assertEquals(0, itemsList.size());
				}
				if (categoryId == 3) {
					String name1 = "";
					List<ItemDTO> itemsList = dao.findByCondition(categoryId, name1);
					assertNotNull(itemsList);
					assertEquals(20, itemsList.size());
					ItemDTO item = itemsList.get(11);
					assertEquals(12, item.getItemId());
					assertEquals("鞄A", item.getItemName());
					assertEquals("青色", item.getColor());
					assertEquals("東京鞄店", item.getManufacturer());
					assertEquals(1980, item.getPrice());

					String name2 = "A";
					itemsList = dao.findByCondition(categoryId, name2);
					assertNotNull(itemsList);
					assertEquals(3, itemsList.size());
					item = itemsList.get(0);
					assertEquals(4, item.getItemId());
					assertEquals("ストローハット PART2", item.getItemName());
					assertEquals("青色", item.getColor());
					assertEquals("(株)ストローハットジャパン", item.getManufacturer());
					assertEquals(4480, item.getPrice());

					String name3 = "aaaa";
					itemsList = dao.findByCondition(categoryId, name3);
					assertNotNull(itemsList);
					assertEquals(0, itemsList.size());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}
	}

	@Test
	void testFindById_1() {
		try (Connection conn = ConnectionUtil.getConnection(null)) {
			ItemDAO dao = new ItemDAO(conn);
			int itemId = 1;
			List<ItemDTO> itemsDetailList = dao.findById(itemId);
			assertNotNull(itemsDetailList);
			assertEquals(1, itemsDetailList.size());
			ItemDTO item = itemsDetailList.get(0);
			assertEquals("麦わら帽子", item.getItemName());
			assertEquals("黄色", item.getColor());
			assertEquals("日本帽子製造", item.getManufacturer());
			assertEquals(4980, item.getPrice());
			assertEquals(12, item.getStock());

		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}
	}

	@Test
	void testFindById_10() {
		try (Connection conn = ConnectionUtil.getConnection(null)) {
			ItemDAO dao = new ItemDAO(conn);
			int itemId = 10;
			List<ItemDTO> itemsDetailList = dao.findById(itemId);
			assertNotNull(itemsDetailList);
			assertEquals(1, itemsDetailList.size());
			ItemDTO item = itemsDetailList.get(0);
			assertEquals("ベレー帽", item.getItemName());
			assertEquals("青色", item.getColor());
			assertEquals("東京帽子店", item.getManufacturer());
			assertEquals(3200, item.getPrice());
			assertEquals(8, item.getStock());

		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}
	}

	@Test
	void testFindByNoId() {
		try (Connection conn = ConnectionUtil.getConnection(null)) {
			ItemDAO dao = new ItemDAO(conn);
			int itemId = 0;
			List<ItemDTO> itemsDetailList = dao.findById(itemId);
			itemsDetailList = dao.findById(itemId);
			assertNotNull(itemsDetailList);
			assertEquals(0, itemsDetailList.size());

		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}
	}

}
