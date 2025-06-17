package jp.co.brmy.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;

import jp.co.brmy.dao.AdministratorsLoginDAO;
import jp.co.brmy.dto.AdministratorsDTO;
import jp.co.brmy.util.ConnectionUtil;

public class AdminLoginService {

	public AdministratorsDTO findById(String adminId, String password) throws SQLException, ServletException {
		String lookupstring = "java:comp/env/jdbc/ecsite";
		try (Connection conn = ConnectionUtil.getConnection(lookupstring)) {
			AdministratorsLoginDAO alDAO = new AdministratorsLoginDAO(conn);
			AdministratorsDTO dto = null;

			dto = alDAO.findById(adminId);

			if (dto != null && dto.getPassword().equals(password)) {
				return dto;
			} else {
				dto = null;
			}
			return dto;
		}
	}
}
