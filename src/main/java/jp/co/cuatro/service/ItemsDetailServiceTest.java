package jp.co.cuatro.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.cuatro.dto.ItemDTO;
import jp.co.cuatro.util.TestBase;

class ItemsDetailServiceTest extends TestBase {

	@BeforeEach
	void setUp() throws Exception {
		String sqlFilePath = "/init_data.sql";
		super.initSQLFiles(sqlFilePath);
	}

	@Test
	void findById() throws Exception {

		ItemsDetailService item = new ItemsDetailService();

		ItemDTO items = item.execute(1);
		assertNotNull(items);
		items = item.execute(10);
		assertNotNull(items);

	}

	void findByNoId() throws Exception {

		ItemsDetailService item = new ItemsDetailService();

		ItemDTO items = item.execute(0);
		assertNotNull(items);

	}
}
