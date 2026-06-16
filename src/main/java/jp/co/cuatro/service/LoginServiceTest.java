package jp.co.cuatro.service;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.cuatro.dto.UsersDTO;
import jp.co.cuatro.util.TestBase;

class LoginServiceTest extends TestBase {

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
	 * ユーザID・パスワード共に正しい場合のテスト
	 */
	@Test
	void testFindById1() {

		LoginService ls = new LoginService();
		try {
			UsersDTO user = ls.execute("user1", "userpass1");

			assertNotNull(user);

			user = ls.execute("user1", "");

			assertNull(user);

		} catch (Exception e) {

			e.printStackTrace();
			fail(e);
		}

	}

	/**
	 * ユーザIDが正しいが、パスワードが間違っている場合のテスト
	 */
	@Test
	void testFindById2() {
		LoginService ls = new LoginService();
		try {
			//パスワードが間違い
			UsersDTO user = ls.execute("user1", "");
			assertNull(user);

		} catch (Exception e) {

			e.printStackTrace();
			fail(e);
		}
	}

	/**
	 * ユーザIDは間違っているが、パスワードが正しい場合のテスト
	 */
	@Test
	void testFindById3() {
		LoginService ls = new LoginService();
		try {

			//ユーザIDが間違い
			UsersDTO user = ls.execute("", "userpass1");
			assertNull(user);

		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			fail(e);
		}
	}

	/**
	 * ユーザID・パスワードともに間違っている場合のテスト
	 */
	@Test
	void testFindById4() {
		LoginService ls = new LoginService();
		try {

			//ID・パスワード両方間違い
			UsersDTO user = ls.execute("", "");
			assertNull(user);

		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			fail(e);
		}
	}

}
