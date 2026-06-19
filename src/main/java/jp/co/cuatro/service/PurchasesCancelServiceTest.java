package jp.co.cuatro.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import jp.co.cuatro.dto.PurchasesDTO;
import jp.co.cuatro.util.TestBase;

class PurchasesCancelServiceTest extends TestBase {

	@BeforeEach
	void setUp() throws Exception {
		String sqlFilePath = "/init_data.sql";
		super.initSQLFiles(sqlFilePath);
	}

	@Test
	void testExecute() {
		try {

			int testPurchaseId = 1;

			PurchasesCancelService service = new PurchasesCancelService();
			PurchasesDTO result = service.execute(testPurchaseId);

			assertNotNull(result);
			assertEquals(testPurchaseId, result.getPurchaseId());

		} catch (Exception e) {
			e.printStackTrace();
			fail("キャンセル処理中にエラーが発生しました: " + e.getMessage());
		}
	}

}
