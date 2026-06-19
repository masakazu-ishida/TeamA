package jp.co.cuatro.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.cuatro.dto.CartDTO;
import jp.co.cuatro.util.ConnectionUtil;
import jp.co.cuatro.util.TestBase;

class CartDAOTest extends TestBase {

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
	 * 主キーが存在する場合のテスト
	 */
	@Test
	void testFindByUserId1() {

		try (Connection conn = ConnectionUtil.getConnection(null)) {
			CartDAO dao = new CartDAO(conn);
			List<CartDTO> cartList = dao.findByUserId("user1");

			assertNotNull(cartList);
			assertEquals(1, cartList.size());

			for (CartDTO cart : cartList) {
				assertEquals("麦わら帽子", cart.getItem().getItemName());
				assertEquals("黄色", cart.getItem().getColor());
				assertEquals("日本帽子製造", cart.getItem().getManufacturer());
				assertEquals(4980, cart.getItem().getPrice());
				assertEquals(1, cart.getAmount());
				assertEquals(1, cart.getItemId());
				assertEquals(1, cart.getItem().getItemId());
				assertEquals("user1", cart.getUserId());
				assertEquals(LocalDate.of(2026, 6, 15), cart.getBookedDate());
				//先頭だけDTOの中身をチェック
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}

	}

	/**
	 * 主キーが存在しない場合のテスト
	 */
	@Test
	void testFindByUserId2() {
		//JUnitテストでは引数はNULLでよい。
		try (Connection conn = ConnectionUtil.getConnection(null)) {
			CartDAO dao = new CartDAO(conn);
			List<CartDTO> cartList = dao.findByUserId("999999ABVS?(%#$");

			assertNotNull(cartList);
			assertEquals(0, cartList.size());

		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}
	}

	/**
	 * データの追加とID検索のテスト
	 */
	@Test
	void testInsertAndFind() {
		//JUnitテストでは引数はNULLでよい。
		try (Connection conn = ConnectionUtil.getConnection(null)) {
			CartDAO dao = new CartDAO(conn);
			CartDTO newCart = new CartDTO();

			newCart.setUserId("user1");
			newCart.setItemId(2);
			newCart.setAmount(2);
			newCart.setBookedDate(LocalDate.now());

			int result = dao.insert(newCart);

			assertEquals(1, result);

			CartDTO checkedCart = dao.findByUserAndItem("user1", 2);

			assertNotNull(checkedCart);
			assertEquals("user1", checkedCart.getUserId());
			assertEquals(2, checkedCart.getItemId());
			assertEquals(2, checkedCart.getAmount());

		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}
	}

	/**
	 * データの更新のテスト
	 */
	@Test
	void testUpdate() {
		//JUnitテストでは引数はNULLでよい。
		try (Connection conn = ConnectionUtil.getConnection(null)) {
			CartDAO dao = new CartDAO(conn);
			CartDTO updateCart = new CartDTO();

			updateCart.setUserId("user1");
			updateCart.setItemId(1);
			updateCart.setAmount(3);
			updateCart.setBookedDate(LocalDate.now());

			int result = dao.update(updateCart);

			assertEquals(1, result);

			CartDTO checkedCart = dao.findByUserAndItem("user1", 1);

			assertNotNull(checkedCart);
			assertEquals("user1", checkedCart.getUserId());
			assertEquals(1, checkedCart.getItemId());
			assertEquals(3, checkedCart.getAmount());

		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}
	}

	/**
	 * ユーザーIDと商品IDをもとに情報を持ってくる場合のテスト
	 */
	@Test
	void testFindByUserIdAndItem() {

		try (Connection conn = ConnectionUtil.getConnection(null)) {
			CartDAO dao = new CartDAO(conn);
			CartDTO cart = dao.findByUserAndItem("user1", 1);

			assertNotNull(cart);

			assertEquals("麦わら帽子", cart.getItem().getItemName());
			assertEquals("日本帽子製造", cart.getItem().getManufacturer());
			assertEquals(4980, cart.getItem().getPrice());
			assertEquals(1, cart.getAmount());
			assertEquals("user1", cart.getUserId());
			assertEquals(1, cart.getItemId());
			assertEquals(1, cart.getItem().getItemId());
			assertEquals(1, cart.getAmount());
			assertEquals(LocalDate.of(2026, 6, 15), cart.getBookedDate());
			//先頭だけDTOの中身をチェック

		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}

	}

	/**
	 * データの削除のテスト
	 */
	@Test
	void testDelete() {
		//JUnitテストでは引数はNULLでよい。
		try (Connection conn = ConnectionUtil.getConnection(null)) {
			CartDAO dao = new CartDAO(conn);

			CartDTO checkedCart = dao.findByUserAndItem("user1", 1);

			assertNotNull(checkedCart);
			assertEquals("user1", checkedCart.getUserId());
			assertEquals(1, checkedCart.getItemId());
			assertEquals(1, checkedCart.getAmount());
			assertEquals(LocalDate.of(2026, 6, 15), checkedCart.getBookedDate());

			int result = dao.delete(checkedCart);
			assertEquals(1, result);

			CartDTO deletedCart = dao.findByUserAndItem("user1", 1);
			assertNull(deletedCart);

		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}
	}

	/**
	 * 購入処理
	 * カートの中身が削除できているか
	 */
	@Test
	void testDeleteCartItem() {

		try (Connection conn = ConnectionUtil.getConnection(null)) {
			CartDAO dao = new CartDAO(conn);

			// 削除する前に、対象のデータ(user_id：user1、item_id：1)が存在するかチェック
			CartDTO checkedCart = dao.findByUserAndItem("user1", 1);
			assertNotNull(checkedCart);

			dao.deleteCartItem(conn, "user1", 1);

			// 削除したはずのデータをもう一度検索し、nullになっているかチェック
			CartDTO deletedCart = dao.findByUserAndItem("user1", 1);
			assertNull(deletedCart);

		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}
	}

}