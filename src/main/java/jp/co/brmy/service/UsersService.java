package jp.co.brmy.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;

import jp.co.brmy.dao.UsersDAO;
import jp.co.brmy.dto.UsersDTO;
import jp.co.brmy.util.CommonConstants;
import jp.co.brmy.util.ConnectionUtil;

public class UsersService {

	public UsersDTO findById(String usersId, String password) throws SQLException, ServletException {

		try (Connection conn = ConnectionUtil.getConnection(CommonConstants.LOOKUP_NAME)) {
			UsersDAO dao = new UsersDAO(conn);
			UsersDTO dto = null;

			dto = dao.findById(usersId);

			if (dto != null) {
				if (dto.getPassword().equals(password)) {

					return dto;
				} else {
					dto = null;
				}

			}
			return dto;
		}
	}

}
