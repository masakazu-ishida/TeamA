package jp.co.cuatro.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import jakarta.servlet.ServletException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.cuatro.dto.CartDTO;
import jp.co.cuatro.util.TestBase;

class CartDeleteConfirmationServiceTest extends TestBase {

	/**
	 * 
	 * 以下の初期化メソッドは何も考えずまるごとコピー
	 * テストメソッド実行の度に毎回init_data.sqlが実行され、
	 * DBの中身が初期化される
	 * */
	@BeforeEach
	void setUp() throws Exception {
		String sqlFilePath = "/init_data.sql";
		super.initSQLFiles(sqlFilePath);
	}

	/**
	 * カート内に存在する商品の削除確認表示のテスト
	 */
	@Test
	void testCartDeleteConfirm() {

		CartDeleteConfirmationService svc = new CartDeleteConfirmationService();
		try {

			CartDTO confirmCart = new CartDTO();

			confirmCart.setUserId("user1");
			confirmCart.setItemId(1);

			CartDTO resultCart = svc.execute(confirmCart);

			assertNotNull(resultCart);
			assertEquals("user1", resultCart.getUserId());
			assertEquals(1, resultCart.getItemId());
			assertEquals(1, resultCart.getAmount());
			assertEquals(LocalDate.of(2026, 6, 15), resultCart.getBookedDate());

		} catch (ServletException e) {

			e.printStackTrace();
			fail(e);
		}

	}

}
