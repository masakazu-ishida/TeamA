package jp.co.cuatro.service;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.cuatro.dao.CartDAO;
import jp.co.cuatro.dto.CartDTO;
import jp.co.cuatro.util.ConnectionUtil;
import jp.co.cuatro.util.TestBase;

class CartDeleteServiceTest extends TestBase {

	@BeforeEach
	void setUp() throws Exception {
		String sqlFilePath = "/init_data.sql";
		super.initSQLFiles(sqlFilePath);
	}

	/**
	 * カート内に存在する商品削除のテスト
	 */
	@Test
	void testDelete() {

		CartDeleteService svc = new CartDeleteService();

		String jndiName = "java:comp/env/jdbc/ecsite";
		try (Connection conn = ConnectionUtil.getConnection(jndiName)) {
			//Connectionをコンストラクタに渡す
			CartDAO dao = new CartDAO(conn);

			CartDTO deleteCart = new CartDTO();

			deleteCart.setUserId("user1");
			deleteCart.setItemId(1);

			CartDTO resultCart = svc.execute(deleteCart);

			assertNotNull(resultCart);
			assertEquals("user1", resultCart.getUserId());
			assertEquals(1, resultCart.getItemId());

			CartDTO checkCart = dao.findByUserAndItem("user1", 1);
			assertNull(checkCart);
		} catch (Exception e) {

			e.printStackTrace();
			fail(e);
		}
	}
}
