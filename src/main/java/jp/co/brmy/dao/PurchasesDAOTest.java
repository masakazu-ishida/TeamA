package jp.co.brmy.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.brmy.dto.PurchaseDetailsDTO;
import jp.co.brmy.dto.PurchasesDTO;
import jp.co.brmy.util.ConnectionUtil;

class PurchasesDAOTest {

	@BeforeEach
	void init() {
		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {
			conn.setAutoCommit(false);
			ItemsInCartDAO dao = new ItemsInCartDAO(conn);
			try {
				dao.insertBatch("sqlFiles/init.sql");

				conn.commit();
			} catch (Exception e) {
				conn.rollback();
				throw e;
			}
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	@Test
	void testInsert() {
		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {
			PurchasesDAO dao = new PurchasesDAO(conn);
			PurchasesDTO dto = new PurchasesDTO();

			dto.setPurchaseUser("user");
			dto.setDestination("東京");
			String strDate = "2020-10-23";
			java.text.SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date expected = dateFormat.parse(strDate);
			dto.setPurchaseDate(expected);
			dao.insert(dto);

			PurchaseDetailsDTO detailsDTO = new PurchaseDetailsDTO();
			PurchaseDetailsDAO detailsDAO = new PurchaseDetailsDAO(conn);
			detailsDTO.setPurchaseId(2);
			detailsDTO.setAmount(1);
			detailsDTO.setItemId(1);

			detailsDAO.insert(detailsDTO);

			PurchasesDTO dto1 = new PurchasesDTO();
			dto1 = dao.findById(2);

			assertEquals(2, dto1.getPurchaseId());
			assertEquals("user", dto1.getPurchaseUser());

			assertEquals(expected, dto1.getPurchaseDate());
			assertEquals("東京", dto1.getDestination());
			assertEquals(false, dto1.getCansel());

		} catch (

		Exception e) {
			e.printStackTrace();
			fail("まだ実装されていません");

		}

	}

	@Test
	void testUpdate() {
		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {
			PurchasesDAO dao = new PurchasesDAO(conn);
			dao.update(1);

			PurchasesDTO dto = new PurchasesDTO();
			dto = dao.findById(1);

			assertEquals(true, dto.getCansel());

		} catch (

		Exception e) {
			e.printStackTrace();
			fail("まだ実装されていません");

		}
	}

	@Test
	void testFindById() {
		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {
			PurchasesDAO dao = new PurchasesDAO(conn);
			PurchasesDTO dto = new PurchasesDTO();
			dto = dao.findById(1);

			assertEquals(1, dto.getPurchaseId());
			assertEquals("user", dto.getPurchaseUser());
			String strDate = "2020-10-23";
			java.text.SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date expected = dateFormat.parse(strDate);
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
	}

	@Test
	void testFindByIdNull() {
		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {
			PurchasesDAO dao = new PurchasesDAO(conn);
			PurchasesDTO dto = new PurchasesDTO();
			dto = dao.findById(99);
			assertNull(dto);

		} catch (Exception e) {
			e.printStackTrace();
			fail("まだ実装されていません");

		}

	}

	@Test
	void testFindAll() {
		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {
			PurchasesDAO dao = new PurchasesDAO(conn);
			//
			PurchasesDTO dto1 = new PurchasesDTO();

			dto1.setPurchaseUser("user");
			dto1.setDestination("東京");
			String strDate = "2020-10-20";
			java.text.SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date expected = dateFormat.parse(strDate);
			dto1.setPurchaseDate(expected);
			dao.insert(dto1);

			PurchaseDetailsDTO detailsDTO = new PurchaseDetailsDTO();
			PurchaseDetailsDAO detailsDAO = new PurchaseDetailsDAO(conn);
			detailsDTO.setPurchaseId(2);
			detailsDTO.setAmount(1);
			detailsDTO.setItemId(1);

			detailsDAO.insert(detailsDTO);
			//
			List<PurchasesDTO> dto = new ArrayList<>();
			dto = dao.findAll();

			int i = 0;
			for (PurchasesDTO purch : dto) {
				if (i == 0) {
					assertEquals(1, purch.getPurchaseId());
					assertEquals("user", purch.getPurchaseUser());
					strDate = "2020-10-23";
					expected = dateFormat.parse(strDate);
					assertEquals(expected, purch.getPurchaseDate());
					assertEquals("鳥取", purch.getDestination());
					assertEquals(false, purch.getCansel());
					List<PurchaseDetailsDTO> list = purch.getPurchaseDetailsDTO();
					for (PurchaseDetailsDTO detail : list) {
						assertEquals("麦わら帽子", detail.getItemsDTO().getName());
						assertEquals("日本帽子製造", detail.getItemsDTO().getManufacturer());
						assertEquals("黄色", detail.getItemsDTO().getColor());
						assertEquals(4980, detail.getItemsDTO().getPrice());
						assertEquals(12, detail.getItemsDTO().getStock());

						assertEquals(2, detail.getAmount());
						break;
					}
				}
				if (i == 1) {
					assertEquals(2, purch.getPurchaseId());
					assertEquals("user", purch.getPurchaseUser());
					strDate = "2020-10-20";
					expected = dateFormat.parse(strDate);
					assertEquals(expected, purch.getPurchaseDate());
					assertEquals("東京", purch.getDestination());
					assertEquals(false, purch.getCansel());
					List<PurchaseDetailsDTO> list = purch.getPurchaseDetailsDTO();
					for (PurchaseDetailsDTO detail : list) {
						assertEquals("麦わら帽子", detail.getItemsDTO().getName());
						assertEquals("日本帽子製造", detail.getItemsDTO().getManufacturer());
						assertEquals("黄色", detail.getItemsDTO().getColor());
						assertEquals(4980, detail.getItemsDTO().getPrice());
						assertEquals(12, detail.getItemsDTO().getStock());

						assertEquals(1, detail.getAmount());
						break;
					}
				}
				i++;

			}

		} catch (Exception e) {
			e.printStackTrace();
			fail("まだ実装されていません");

		}
	}
}
