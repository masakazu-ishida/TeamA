package jp.co.cuatro.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import jakarta.servlet.ServletException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.cuatro.dto.CartDTO;
import jp.co.cuatro.util.TestBase;

class CartAddServiceTest extends TestBase {

	@BeforeEach
	void setUp() throws Exception {
		String sqlFilePath = "/init_data.sql";
		super.initSQLFiles(sqlFilePath);
	}

	/**
	 * カート追加のテスト
	 */
	@Test
	void testCartAdd() {

		CartAddService svc = new CartAddService();
		try {
			CartDTO inputCart = new CartDTO();

			inputCart.setUserId("user1");
			inputCart.setItemId(2);
			inputCart.setAmount(5);
			inputCart.setBookedDate(LocalDate.now());

			CartDTO resultCart = svc.execute(inputCart);

			assertNotNull(resultCart);
			assertEquals("user1", resultCart.getUserId());
			assertEquals(2, resultCart.getItemId());
			assertEquals(5, resultCart.getAmount());
			assertEquals(LocalDate.now(), resultCart.getBookedDate());

		} catch (ServletException e) {

			e.printStackTrace();
			fail(e);
		}

	}

	/**
	 * カートに既に同じ商品IDの商品が入っており、数量を更新する場合のテスト
	 */
	@Test
	void testCartUpdate() {

		CartAddService svc = new CartAddService();
		try {
			CartDTO inputCart = new CartDTO();

			inputCart.setUserId("user1");
			inputCart.setItemId(1);
			inputCart.setAmount(4);
			inputCart.setBookedDate(LocalDate.now());

			CartDTO resultCart = svc.execute(inputCart);

			assertNotNull(resultCart);
			assertEquals("user1", resultCart.getUserId());
			assertEquals(1, resultCart.getItemId());
			assertEquals(5, resultCart.getAmount());
			assertEquals(LocalDate.of(2026, 6, 15), resultCart.getBookedDate());

		} catch (ServletException e) {

			e.printStackTrace();
			fail(e);
		}

	}

}
