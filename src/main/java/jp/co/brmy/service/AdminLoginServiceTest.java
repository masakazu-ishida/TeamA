package jp.co.brmy.service;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.brmy.dao.BaseDAO;
import jp.co.brmy.dto.AdministratorsDTO;
import jp.co.brmy.util.ConnectionUtil;

class AdminLoginServiceTest {

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
	void testLoginServce() throws SQLException, ServletException {

		AdminLoginService ALS = new AdminLoginService();
		AdministratorsDTO dto = null;

		dto = ALS.findById("admin", "admin");
		assertNotNull(dto);
		assertEquals("admin", dto.getAdminId());
		assertEquals("admin", dto.getPassword());
		assertEquals("管理者", dto.getName());

		dto = ALS.findById("admin", "pass");

		assertNull(dto);
	}
}
