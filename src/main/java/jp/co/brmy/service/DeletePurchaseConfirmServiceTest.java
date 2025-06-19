package jp.co.brmy.service;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.brmy.dao.BaseDAO;
import jp.co.brmy.dao.PurchasesDAO;
import jp.co.brmy.dto.PurchaseDetailsDTO;
import jp.co.brmy.dto.PurchasesDTO;
import jp.co.brmy.util.ConnectionUtil;

class DeletePurchaseConfirmServiceTest {

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
	void testFindById() {
		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {
			PurchasesDAO dao = new PurchasesDAO(conn);
			PurchasesDTO dto = new PurchasesDTO();
			dto = dao.findById(1);
			String strDate = "2020-10-23";
			java.text.SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date expected = dateFormat.parse(strDate);

			assertEquals(1, dto.getPurchaseId());
			assertEquals("user", dto.getPurchaseUser());
			expected = dateFormat.parse(strDate);
			assertEquals(expected, dto.getPurchaseDate());
			assertEquals("鳥取", dto.getDestination());
			assertEquals(false, dto.getCansel());
			List<PurchaseDetailsDTO> list = dto.getPurchaseDetailsDTO();
			for (PurchaseDetailsDTO detail : list) {
				assertEquals("麦わら帽子", detail.getItemsDTO().getName());
				assertEquals("日本帽子製造", detail.getItemsDTO().getManufacturer());
				assertEquals("黄色", detail.getItemsDTO().getColor());
				assertEquals(4980, detail.getItemsDTO().getPrice());
				assertEquals(12, detail.getItemsDTO().getStock());

				assertEquals(2, detail.getAmount());
				break;

			}
		} catch (Exception e) {
			e.printStackTrace();
			fail("まだ実装されていません");

		}
		//fail("まだ実装されていません");
	}

}
