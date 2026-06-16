package jp.co.cuatro.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.cuatro.dto.ItemDTO;
import jp.co.cuatro.util.TestBase;

class ItemsSerchServiceTest extends TestBase {
	@BeforeEach
	void setUp() throws Exception {
		String sqlFilePath = "/init_data.sql";
		super.initSQLFiles(sqlFilePath);
	}

	@Test
	void testFindAll() throws Exception {

		ItemsSerchService item = new ItemsSerchService();

		List<ItemDTO> itemsList = item.execute(1, "");
		assertNotNull(itemsList);
		assertEquals(11, itemsList.size());

		itemsList = item.execute(1, "帽");
		assertNotNull(itemsList);
		assertEquals(5, itemsList.size());
		itemsList = item.execute(2, "");
		assertNotNull(itemsList);
		assertEquals(9, itemsList.size());

		itemsList = item.execute(2, "E");
		assertNotNull(itemsList);
		assertEquals(1, itemsList.size());

		itemsList = item.execute(3, "");
		assertNotNull(itemsList);
		assertEquals(20, itemsList.size());

		itemsList = item.execute(3, "A");
		assertNotNull(itemsList);
		assertEquals(3, itemsList.size());

		itemsList = item.execute(3, "111");
		assertNotNull(itemsList);
		assertEquals(0, itemsList.size());
	}

}
