package jp.co.cuatro.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.cuatro.dto.UsersDTO;
import jp.co.cuatro.util.ConnectionUtil;
import jp.co.cuatro.util.TestBase;

class UsersDAOTest extends TestBase {

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
				e.printStackTrace();
			}
		}
	}

	/**
	 * 主キーが存在する場合のテスト
	 */
	@Test
	void testFindByIdPKTrue() {
		try (Connection conn = ConnectionUtil.getConnection(null)) {
			UsersDAO dao = new UsersDAO(conn);
			UsersDTO user = dao.findById("user1");

			assertNotNull(user);
			assertEquals("user1", user.getUserId());
			assertEquals("userpass1", user.getPassword());
			assertEquals("鳥取一郎", user.getName());
			assertEquals("鳥取県鳥取市河原町６丁目１０７", user.getAddress());

		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}
	}

	/**
	 * 主キーが存在しない場合のテスト
	 */
	@Test
	void testFindByIdPKFalse() {
		try (Connection conn = ConnectionUtil.getConnection(null)) {
			UsersDAO dao = new UsersDAO(conn);
			UsersDTO user = dao.findById("999999ABVS?(%#$");

			// 主キーが存在しない場合はnullを返す
			assertNull(user);

		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}
	}

	/**
	 * 入力された会員IDがnullの場合のテスト
	 */
	@Test
	void testFindByIdNull() {
		try (Connection conn = ConnectionUtil.getConnection(null)) {
			UsersDAO dao = new UsersDAO(conn);
			UsersDTO user = dao.findById(null);

			// 引数がnullの場合はnullを返す
			assertNull(user);

		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}
	}

	/**
	 * 入力された値が空文字の場合のテスト
	 */
	@Test
	void testFindByIdEmpty() {
		try (Connection conn = ConnectionUtil.getConnection(null)) {
			UsersDAO dao = new UsersDAO(conn);
			UsersDTO user = dao.findById("");

			// 引数が空文字の場合はnullを返す
			assertNull(user);

		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}
	}

}
