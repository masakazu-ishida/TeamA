package jp.co.cuatro.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.cuatro.dto.PurchasesDTO;
import jp.co.cuatro.util.ConnectionUtil;
import jp.co.cuatro.util.TestBase;

class PurchasesDAOTest extends TestBase {
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
				// TODO 自動生成された catch ブロック
				e.printStackTrace();

			}
		}
	}

	@Test
	void testFindPurchaseHistoryByUserId() {
		try (Connection conn = ConnectionUtil.getConnection(null)) {
			PurchasesDAO dao = new PurchasesDAO(conn);

			String testUserId = "user1";

			List<PurchasesDTO> resultList = dao.findPurchaseHistoryByUserId(testUserId);

			assertNotNull(resultList);
			assertEquals(3, resultList.size());

			PurchasesDTO Purchase = resultList.get(0);
			assertEquals("2026-06-17", Purchase.getPurchasedDate());
			assertEquals("ハンチング帽", Purchase.getPurchaseDetailsDTO().getItemDTO().getItemName());
			assertEquals("黄色", Purchase.getPurchaseDetailsDTO().getItemDTO().getColor());
			assertEquals(1980, Purchase.getPurchaseDetailsDTO().getItemDTO().getPrice());
			assertEquals(4, Purchase.getPurchaseDetailsDTO().getAmount());
			assertEquals(null, Purchase.getDestination());
			assertEquals(false, Purchase.isCancel());

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	void testFindPurchaseHistoryByNoUserId() {
		try (Connection conn = ConnectionUtil.getConnection(null)) {
			PurchasesDAO dao = new PurchasesDAO(conn);

			String testUserId = "user0";

			List<PurchasesDTO> resultList = dao.findPurchaseHistoryByUserId(testUserId);

			assertNotNull(resultList);
			assertEquals(0, resultList.size());

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

}
