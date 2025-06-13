package jp.co.brmy.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.brmy.dto.ItemsInCartDTO;
import jp.co.brmy.util.ConnectionUtil;

class ItemsInCartDAOTest {

	@BeforeEach
	void init() {
		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {
			conn.setAutoCommit(false);
			ItemsInCartDAO dao = new ItemsInCartDAO(conn);
			try {
				dao.insertBatch("sqlFiles/ItemInCartTest.sql");
		


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
	void testFindAll() {	
		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {
			ItemsInCartDAO dao=new ItemsInCartDAO(conn);
			List<ItemsInCartDTO> list=new ArrayList<>();
			list=dao.findAll();
			for (ItemsInCartDTO dto : list) {
				assertEquals("user", dto.getUserId());
				assertEquals(1, dto.getItemId());
				assertEquals(2, dto.getAmount());
				//assertEquals((Date"2020-10-20", dto.getBookedDate());
				assertEquals("麦わら帽子", dto.getItemsDTO().getName());
				assertEquals("日本帽子製造", dto.getItemsDTO().getManufacturer());
				assertEquals(1, dto.getItemsDTO().getCategoryId());
				assertEquals("黄色", dto.getItemsDTO().getColor());
				assertEquals(4980, dto.getItemsDTO().getPrice());
				assertEquals(12, dto.getItemsDTO().getStock());
				assertEquals(false, dto.getItemsDTO().isRecommended());


				break;
				
			}
			

	
		}catch (Exception e){
			e.printStackTrace();
			fail("まだ実装されていません");
			
		}
	}
	
	@Test
	void testFindByUserId() {	
		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {
			ItemsInCartDAO dao=new ItemsInCartDAO(conn);
			List<ItemsInCartDTO> list=new ArrayList<>();
			list=dao.findByUserId("user");
			for (ItemsInCartDTO dto : list) {
				assertEquals("user", dto.getUserId());
				assertEquals(1, dto.getItemId());
				assertEquals(2, dto.getAmount());
				//assertEquals((Date"2020-10-20", dto.getBookedDate());
				
				assertEquals("麦わら帽子", dto.getItemsDTO().getName());
				assertEquals("日本帽子製造", dto.getItemsDTO().getManufacturer());
				assertEquals(1, dto.getItemsDTO().getCategoryId());
				assertEquals("黄色", dto.getItemsDTO().getColor());
				assertEquals(4980, dto.getItemsDTO().getPrice());
				assertEquals(12, dto.getItemsDTO().getStock());
				assertEquals(false, dto.getItemsDTO().isRecommended());
				break;
				
			}


	
		}catch (Exception e){
			e.printStackTrace();
			fail("まだ実装されていません");
			
		}
	}
	@Test
	void testFindByUserIdNull() {	
		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {
			ItemsInCartDAO dao=new ItemsInCartDAO(conn);
			List<ItemsInCartDTO> list=new ArrayList<>();
			list=dao.findByUserId("");
			for (ItemsInCartDTO dto : list) {
				fail();
			}
		}catch (Exception e){
			e.printStackTrace();
			fail("まだ実装されていません");
			
		}
	}
	
	@Test
	void testDeleteItemsInCart() {	
		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {
			ItemsInCartDAO dao=new ItemsInCartDAO(conn);			
			int a=dao.deleteItemsInCart("user");
			assertEquals(1, a);
			
			List<ItemsInCartDTO> list=new ArrayList<>();
			list=dao.findByUserId("user");
			for (ItemsInCartDTO dto : list) {
				fail();
			}
			

	
		}catch (Exception e){
			e.printStackTrace();
			fail("まだ実装されていません");
			
		}
	}

	@Test
	void testInsertItemsInCart() {	
		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {
			ItemsInCartDAO dao=new ItemsInCartDAO(conn);
			ItemsInCartDTO dto=new ItemsInCartDTO();
			
			dao.deleteItemsInCart("user");
			
			dto.setItemId(2);
			dto.setUserId("user");
			dto.setAmount(4);
			
			int a=dao.insertItemsInCart(dto);
			assertEquals(1, a);
			
			
			List<ItemsInCartDTO> list=dao.findByUserId("user");
			for (ItemsInCartDTO dto2 : list) {
				assertEquals("user", dto2.getUserId());
				assertEquals(2, dto2.getItemId());
				assertEquals(4, dto2.getAmount());
				//assertEquals((Date"2020-10-20", dto2.getBookedDate());
				assertEquals("ストローハット", dto2.getItemsDTO().getName());
				assertEquals("(株)ストローハットジャパン", dto2.getItemsDTO().getManufacturer());
				assertEquals(1, dto2.getItemsDTO().getCategoryId());
				assertEquals("茶色", dto2.getItemsDTO().getColor());
				assertEquals(3480, dto2.getItemsDTO().getPrice());
				assertEquals(15, dto2.getItemsDTO().getStock());
				assertEquals(true, dto2.getItemsDTO().isRecommended());


				break;
				
			}
	

	
		}catch (Exception e){
			e.printStackTrace();
			fail("まだ実装されていません");
			
		}
	}


}
