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
				assertEquals("1", cart.getItemId());
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

			assertNull(cartList);

		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}
	}

}