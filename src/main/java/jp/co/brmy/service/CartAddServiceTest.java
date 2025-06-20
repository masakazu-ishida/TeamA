package jp.co.brmy.service;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.brmy.dao.BaseDAO;
import jp.co.brmy.dao.ItemsInCartDAO;
import jp.co.brmy.dto.ItemsInCartDTO;
import jp.co.brmy.util.ConnectionUtil;

class CartAddServiceTest {

	@BeforeEach
	void init() {
		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {
			ConnectionUtil.mode = ConnectionUtil.MODE.TEST;

			BaseDAO dao = new BaseDAO(conn);
			try {
				dao.insertBatch("sqlFiles/init.sql");

			} catch (Exception e) {
				throw e;
			}
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	void testcartadd() {
		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {
			ItemsInCartDAO dao = new ItemsInCartDAO(conn);
			CartAddService serv = new CartAddService();

			dao.deleteItemsInCart("user");

			serv.cartadd(2, 4, "user");

			List<ItemsInCartDTO> list = dao.findByUserId("user");
			for (ItemsInCartDTO dto2 : list) {
				assertEquals("user", dto2.getUserId());
				assertEquals(2, dto2.getItemId());
				assertEquals(4, dto2.getAmount());
				assertEquals("ストローハット", dto2.getItemsDTO().getName());
				assertEquals("(株)ストローハットジャパン", dto2.getItemsDTO().getManufacturer());
				assertEquals(1, dto2.getItemsDTO().getCategoryId());
				assertEquals("茶色", dto2.getItemsDTO().getColor());
				assertEquals(3480, dto2.getItemsDTO().getPrice());
				assertEquals(15, dto2.getItemsDTO().getStock());
				assertEquals(true, dto2.getItemsDTO().isRecommended());

				break;

			}

		} catch (Exception e) {
			e.printStackTrace();
			fail("まだ実装されていません");

		}
	}
}
