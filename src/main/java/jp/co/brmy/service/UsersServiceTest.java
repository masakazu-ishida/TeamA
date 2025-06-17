package jp.co.brmy.service;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.brmy.dao.BaseDAO;
import jp.co.brmy.dto.UsersDTO;
import jp.co.brmy.util.ConnectionUtil;

public class UsersServiceTest {

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

		UsersService US = new UsersService();
		UsersDTO dto = null;

		dto = US.findById("user", "userpass");
		assertNotNull(dto);
		assertEquals("user", dto.getUserId());
		assertEquals("userpass", dto.getPassword());
		assertEquals("鳥取一郎", dto.getName());
		assertEquals("鳥取県鳥取市河原町６丁目１０７", dto.getAddress());

		dto = US.findById("user", "abc");

		assertNull(dto);
	}
}
