package jp.co.cuatro.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import jp.co.cuatro.dto.CartDTO;
import jp.co.cuatro.util.TestBase;

public class PurchaseDetailsServiceTest extends TestBase {

	/**
	 * 
	 * 以下の初期化メソッドは何も考えずまるごとコピー
	 * テストメソッド実行の度に毎回init_data.sqlが実行され、
	 * DBの中身が初期化される
	 * */
	@Before
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
	@AfterClass
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
	 * 処理が成功して、trueが返される
	 */
	@Test
	public void testExecuteTrue() {

		// テスト用注文情報の設定
		List<CartDTO> cartList = new ArrayList<>();
		CartDTO cart = new CartDTO();
		cart.setItemId(1);
		cart.setAmount(1);
		cartList.add(cart);

		// 業務処理の実行
		PurchaseDetailsService service = new PurchaseDetailsService();
		service.execute("user1", "鳥取県鳥取市河原町６丁目１０７");

		// 戻り値がtrueかどうかチェック
	}

	/**
	 * 購入処理
	 * 処理が失敗して、falseが返される
	 */
	@Test
	public void testExecuteFalse() {
		// テスト用注文情報の設定
		List<CartDTO> cartList = new ArrayList<>();
		CartDTO cart = new CartDTO();
		cart.setItemId(9999);
		cart.setAmount(1);
		cartList.add(cart);

		// 業務処理の実行
		PurchaseDetailsService service = new PurchaseDetailsService();
		service.execute("user1", "鳥取県鳥取市河原町６丁目１０７");
	}
}
