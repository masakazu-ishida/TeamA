package jp.co.cuatro.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import jakarta.servlet.ServletException;

import org.junit.jupiter.api.Test;

import jp.co.cuatro.dto.CartDTO;

class CartDeleteConfirmationServiceTest {

	/**
	 * カート内に存在する商品の削除確認表示のテスト
	 */
	@Test
	void testCartDeleteConfirm1() {

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
