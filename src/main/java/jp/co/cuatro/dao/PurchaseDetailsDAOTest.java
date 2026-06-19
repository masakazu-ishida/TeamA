package jp.co.cuatro.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;

import jp.co.cuatro.dto.PurchaseDetailsDTO;
import jp.co.cuatro.util.ConnectionUtil;
import jp.co.cuatro.util.TestBase;

/**
 * DAOの単体テスト
 * スーパークラスに必ずTestBaseを指定する。
 * 
 * 単体テストの観点
 * ・網羅率：ブランチカバレッジ
 * ・検索系：DTOのフィールドに値が期待どおり格納されるか？Listの場合期待する件数が格納されるか？
 * ・更新系：DBの状態が期待どおりか？
 * 
 */
public class PurchaseDetailsDAOTest extends TestBase {

	/**
	 * 
	 * 以下の初期化メソッドは何も考えずまるごとコピー
	 * テストメソッド実行の度に毎回init_data.sqlが実行され、
	 * DBの中身が初期化される
	 * */
	@BeforeEach
	public void setUp() throws Exception {
		String sqlFilePath = "/init_data.sql";
		super.initSQLFiles(sqlFilePath);

	}

	/**
	以下の後始末メソッドは何も考えずまるごとコピー
	 * テストメソッドが全て完了した後、必ず実行され、
	 * DBのコネクションがクローズされる。なお、クローズされるのは
	 * /init_data.sqlで初期化するコネクション
	 * */
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

	/**
	 * 購入処理
	 * 注文明細が登録できたか
	 */
	@Test
	public void testInsert() {

		try (Connection conn = ConnectionUtil.getConnection(null)) {
			PurchaseDetailsDAO dao = new PurchaseDetailsDAO(conn);

			// 追加前の前提件数が3件であることをチェック
			List<PurchaseDetailsDTO> beforeList = dao.findByPurchaseId(1);
			assertNotNull(beforeList);
			assertEquals(4, beforeList.size());

			PurchaseDetailsDTO newDetail = new PurchaseDetailsDTO();
			newDetail.setPurchaseId(1); // 注文ID：1
			newDetail.setItemId(1); // 商品ID：1
			newDetail.setAmount(5); // 数量：5個

			int num = dao.insert(conn, newDetail);
			assertEquals(1, num);

			// 明細一覧を取得
			List<PurchaseDetailsDTO> afterList = dao.findByPurchaseId(1);
			assertNotNull(afterList);

			// 明細が1件増えているか
			assertEquals(5, afterList.size());

			// 4件目に追加された明細が正しいか
			PurchaseDetailsDTO insertedDetail = afterList.get(3);
			assertEquals(1, insertedDetail.getPurchaseId(), "登録した購入IDと一致すること");
			assertEquals(1, insertedDetail.getItemId(), "登録した商品IDと一致すること");
			assertEquals(5, insertedDetail.getAmount(), "登録した数量と一致すること");

		} catch (Exception e) {
			e.printStackTrace();
			fail("予期せぬ例外が発生しました: " + e.getMessage());
		}
	}

}
