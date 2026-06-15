package jp.co.cuatro.dao;

import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;

import jp.co.cuatro.util.TestBase;

public class PurchasesDAOTest {

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
	class UserDAOTest extends TestBase {

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

	}
}
