package jp.co.cuatro.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.cuatro.dto.ItemDTO;
import jp.co.cuatro.dto.PurchaseDetailsDTO;
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
	void testFindByUserId() {
		try (Connection conn = ConnectionUtil.getConnection(null)) {
			PurchasesDAO dao = new PurchasesDAO(conn);

			String testUserId = "user1";

			List<PurchasesDTO> resultList = dao.findByUserId(testUserId);

			assertNotNull(resultList);
			assertEquals(1, resultList.size());
			PurchasesDTO purchase = resultList.get(0);
			assertEquals(1, purchase.getPurchaseId());
			assertEquals("user1", purchase.getPurchasedUser());
			assertEquals("2026-06-18", purchase.getPurchasedDate());
			assertEquals(null, purchase.getDestination());
			assertEquals(false, purchase.isCancel());

			assertNotNull(purchase.getDetailsList());
			assertEquals(3, purchase.getDetailsList().size());

			PurchaseDetailsDTO Detail = purchase.getDetailsList().get(0);

			assertEquals(1, Detail.getPurchaseDetailId());
			assertEquals(1, Detail.getPurchaseId());
			assertEquals(7, Detail.getItemId());
			assertEquals(4, Detail.getAmount());

			ItemDTO item = Detail.getItemDTO();
			assertNotNull(item);
			assertEquals(7, item.getItemId());
			assertEquals("ハンチング帽", item.getItemName());
			assertEquals("日本帽子製造", item.getManufacturer());
			assertEquals(1, item.getCategoryId());
			assertEquals("黄色", item.getColor());
			assertEquals(1980, item.getPrice());
			assertEquals(20, item.getStock());
			assertEquals(false, item.isRecommended());

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	void testFindByNoUserId() {
		try (Connection conn = ConnectionUtil.getConnection(null)) {
			PurchasesDAO dao = new PurchasesDAO(conn);

			String testUserId = "user0";

			List<PurchasesDTO> resultList = dao.findByUserId(testUserId);

			assertNotNull(resultList);
			assertEquals(0, resultList.size());

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	void testFindPurchaseForCancel() {
		try (Connection conn = ConnectionUtil.getConnection(null)) {
			PurchasesDAO dao = new PurchasesDAO(conn);

			int testPurchaseId = 1;

			PurchasesDTO result = dao.findById(testPurchaseId);

			assertNotNull(result);
			assertEquals("user1", result.getPurchasedUser());
			assertEquals("2026-06-18", result.getPurchasedDate());
			assertEquals(null, result.getDestination());
			assertEquals(false, result.isCancel());

			assertNotNull(result.getDetailsList());
			assertEquals(3, result.getDetailsList().size());

			PurchaseDetailsDTO Detail = result.getDetailsList().get(0);

			assertEquals(1, Detail.getPurchaseDetailId());
			assertEquals(1, Detail.getPurchaseId());
			assertEquals(7, Detail.getItemId());
			assertEquals(4, Detail.getAmount());

			ItemDTO item = Detail.getItemDTO();
			assertNotNull(item);
			assertEquals(7, item.getItemId());
			assertEquals("ハンチング帽", item.getItemName());
			assertEquals("日本帽子製造", item.getManufacturer());
			assertEquals(1, item.getCategoryId());
			assertEquals("黄色", item.getColor());
			assertEquals(1980, item.getPrice());
			assertEquals(20, item.getStock());
			assertEquals(false, item.isRecommended());

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	void testFindPurchaseForCancel_NoId() {
		try (Connection conn = ConnectionUtil.getConnection(null)) {
			PurchasesDAO dao = new PurchasesDAO(conn);

			int testPurchaseId = 4;

			PurchasesDTO result = dao.findById(testPurchaseId);

			assertNull(result);

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * 購入処理
	 * 注文情報の登録ができているかテスト
	 */
	@Test
	void testInsert() {
		try (Connection conn = ConnectionUtil.getConnection(null)) {
			PurchasesDAO dao = new PurchasesDAO(conn);

			// テスト用のデータを追加
			PurchasesDTO newPurchase = new PurchasesDTO();
			newPurchase.setPurchasedUser("user1");
			newPurchase.setDestination("鳥取県鳥取市賀露町");
			newPurchase.setPurchasedDate("2026-06-19");

			// 注文親情報の登録
			int generatedId = dao.insert(conn, newPurchase);

			// 登録数チェック
			assertEquals(1, generatedId);

			String spl = "SELECT purchased_user, destination, cancel FROM purchases WHERE purchase_id = ?";

			try (PreparedStatement ps = conn.prepareStatement(spl)) {
				ps.setInt(1, newPurchase.getPurchaseId());

				try (ResultSet rs = ps.executeQuery()) {
					assertTrue(rs.next());
					assertEquals("user1", rs.getString("purchased_user"));
					assertEquals("鳥取県鳥取市賀露町", rs.getString("destination"));
					assertEquals("2026-06-19", rs.getString(""));
					assertFalse(rs.getBoolean("cancel"));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
}
