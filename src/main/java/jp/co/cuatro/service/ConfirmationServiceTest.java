package jp.co.cuatro.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import jakarta.servlet.ServletException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.cuatro.dto.CartDTO;
import jp.co.cuatro.util.TestBase;

/**
 * サービスの単体テスト
 * スーパークラスに必ずTestBaseを指定する。
 * 単体テストの観点
 * ・網羅率：ブランチカバレッジ
 * ・検索系：DTOのフィールドに値が期待どおり格納されるか？Listの場合期待する件数が格納されるか？
 * ・更新系：DBの状態が期待どおりか？
 * 
 * サービスの場合、処理内容によっては単純に検索メソッドを読んで戻り値を返すだけなど）単体テストと
 * ほぼ同じになるケースもあるが、それでも実施する。
 * DAOテストのテストコードの流用ができるはず。
 * 
 */
public class ConfirmationServiceTest extends TestBase {

	@BeforeEach
	void setUp() throws Exception {
		String sqlFilePath = "/init_data.sql";
		super.initSQLFiles(sqlFilePath);
	}

	/**
	 * ユーザIDが正しい場合のテスト
	 */
	@Test
	void testFindByUserId1() {

		CartAllService svc = new CartAllService();
		try {
			List<CartDTO> cartList = svc.execute("user1");

			assertNotNull(cartList);

			assertEquals(1, cartList.size());

			//1件目のデータを取り出して合計計算結果をチェック
			CartDTO cart = cartList.get(0);
			assertEquals(4980, cart.getTotal());

		} catch (ServletException e) {

			e.printStackTrace();
			fail(e);
		}

	}

	/**
	 * ユーザIDが存在しない場合のテスト
	 */
	@Test
	void testFindByUserId2() {
		CartAllService svc = new CartAllService();
		try {
			List<CartDTO> cartList = svc.execute("999999ABVS?(%#$");

			assertNotNull(cartList);
			assertEquals(0, cartList.size());

		} catch (ServletException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			fail(e);
		}
	}

	/**
	 * ユーザIDは存在するがカートが空の場合のテスト
	 */
	@Test
	void testFindByUserId3() {
		CartAllService svc = new CartAllService();
		try {
			List<CartDTO> cartList = svc.execute("user2");

			assertNotNull(cartList);
			assertEquals(0, cartList.size());

		} catch (ServletException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			fail(e);
		}
	}

}

//	/**
//	 * 
//	 * 以下の初期化メソッドは何も考えずまるごとコピー
//	 * テストメソッド実行の度に毎回init_data.sqlが実行され、
//	 * DBの中身が初期化される
//	 * */
//	@BeforeEach
//	void setUp() throws Exception {
//		String sqlFilePath = "/init_data.sql";
//		super.initSQLFiles(sqlFilePath);
//	}
//
//	@Test
//	void testGetUserId() {
//		ConfirmationService service = new ConfirmationService();
//		try {
//			String CheckUserId = "user1";
//			List<CartDTO> List = service.findUserId(CheckUserId);
//			assertNotNull(List);
//			assertEquals(1, List.size());
//			CartDTO List1 = List.get(0);
//			assertEquals("user1", List1.getUserId());
//		} catch (Exception e) {
//			// TODO 自動生成された catch ブロック
//			e.printStackTrace();
//			fail(e);
//		}
//	}
//
//	@Test
//	void testGetUserId1() {
//		ConfirmationService service1 = new ConfirmationService();
//		try {
//			String UnUserId = "9999999";
//			List<CartDTO> List = service1.findUserId(UnUserId);
//			assertEquals(0, List.size());
//		} catch (Exception e) {
//			// TODO 自動生成された catch ブロック
//			e.printStackTrace();
//			fail(e);
//		}
//
//	}
//}
