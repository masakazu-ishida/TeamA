package jp.co.cuatro.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.cuatro.dto.PurchasesDTO;
import jp.co.cuatro.util.TestBase;

class HistoryPurchasesServiceTest extends TestBase {

	@BeforeEach
	void setUp() throws Exception {
		String sqlFilePath = "/init_data.sql";
		super.initSQLFiles(sqlFilePath);
	}

	@Test
	void testExecute() throws Exception {

		HistoryPurchasesService historyPurchases = new HistoryPurchasesService();

		List<PurchasesDTO> List = historyPurchases.execute("user1");
		assertNotNull(List);
		assertEquals(3, List.size());

	}

	@Test
	void testExecute_NotId() throws Exception {

		HistoryPurchasesService historyPurchases = new HistoryPurchasesService();

		List<PurchasesDTO> List = historyPurchases.execute("user0");
		assertNotNull(List);
		assertEquals(0, List.size());

	}

}
