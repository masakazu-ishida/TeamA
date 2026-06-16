package jp.co.cuatro.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import jakarta.servlet.ServletException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.cuatro.dto.CartDTO;
import jp.co.cuatro.util.TestBase;

class CartAllServiceTest extends TestBase {

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

			cartList = svc.execute("user1");

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
