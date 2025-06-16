package jp.co.brmy.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.brmy.dto.PurchasesDTO;
import jp.co.brmy.util.ConnectionUtil;

public class PurchasesDAOTest {

	@BeforeEach
	void init() {
		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {
			conn.setAutoCommit(false);
			ItemsInCartDAO dao = new ItemsInCartDAO(conn);
			try {
				dao.insertBatch("sqlFiles/purchases.sql");
		


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
	void testFindById() {	
		
	
		
		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {
			PurchasesDAO dao = new PurchasesDAO(conn);
			PurchasesDTO purchasesDto = new PurchasesDTO();
			


	
			
			
			
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		
			
			}
	
}
