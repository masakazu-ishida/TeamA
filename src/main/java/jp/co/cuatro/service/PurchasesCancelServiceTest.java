package jp.co.cuatro.service;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import jp.co.cuatro.dao.ItemDAO;
import jp.co.cuatro.dao.PurchasesDAO;
import jp.co.cuatro.dto.ItemDTO;
import jp.co.cuatro.dto.PurchaseDetailsDTO;
import jp.co.cuatro.dto.PurchasesDTO;
import jp.co.cuatro.util.ConnectionUtil;
import jp.co.cuatro.util.TestBase;

class PurchasesCancelServiceTest extends TestBase {

	@BeforeEach
	void setUp() throws Exception {
		String sqlFilePath = "/init_data.sql";
		super.initSQLFiles(sqlFilePath);
	}

	@Test
	void testExecuteSuccess() {
		// テスト用に用意した、キャンセルしたい購入ID（実データに合わせて調整してください）
		int testPurchaseId = 1;

		// 事前に在庫数を確認するためのコネクション（テスト検証用）
		try (Connection conn = ConnectionUtil.getConnection(null)) {
			ItemDAO iDao = new ItemDAO(conn);
			PurchasesDAO pDao = new PurchasesDAO(conn);

			// 1. 【実行前確認】キャンセル対象の注文データを事前に取得し、現在の在庫数を記録しておく
			PurchasesDTO beforePurchase = pDao.findById(testPurchaseId);
			assertNotNull(beforePurchase, "テスト用の購入データが存在しません");

			// 注文に含まれる商品の「実行前の在庫数」を配列やリストで保持
			List<PurchaseDetailsDTO> details = beforePurchase.getDetailsList();
			int[] beforeStocks = new int[details.size()];

			for (int i = 0; i < details.size(); i++) {
				int itemId = details.get(i).getItemDTO().getItemId();
				ItemDTO item = iDao.findById(itemId); // ※DAOにfindByIdがある前提
				beforeStocks[i] = item.getStock();
			}

			// 2. 【テスト対象の実行】サービスを呼び出す
			PurchasesCancelService service = new PurchasesCancelService();
			PurchasesDTO result = service.execute(testPurchaseId);

			// 3. 【検証①】戻り値のDTOが正しく取得できているか
			assertNotNull(result);
			assertEquals(testPurchaseId, result.getPurchaseId());

			// 4. 【検証②】DB上のキャンセルフラグが true に更新されているか
			PurchasesDTO afterPurchase = pDao.findById(testPurchaseId);
			assertTrue(afterPurchase.isCancel(), "キャンセルフラグがtrueになっていません");

			// 5. 【検証③】すべての商品の在庫が、購入数量分だけ正しく増えているか
			for (int i = 0; i < details.size(); i++) {
				PurchaseDetailsDTO detail = details.get(i);
				int itemId = detail.getItemDTO().getItemId();
				int buyAmount = detail.getAmount(); // 購入されていた数量

				// 実行後の最新の在庫数をDBから取得
				ItemDTO afterItem = iDao.findById(itemId);

				// 期待値：実行前の在庫数 + キャンセルで戻った数量
				int expectedStock = beforeStocks[i] + buyAmount;
				assertEquals(expectedStock, afterItem.getStock(),
						"商品ID: " + itemId + " の在庫更新数が一致しません");
			}

		} catch (Exception e) {
			e.printStackTrace();
			fail("テスト中に例外が発生しました: " + e.getMessage());
		}
	}

}
