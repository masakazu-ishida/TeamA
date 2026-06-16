package jp.co.cuatro.service;

import java.sql.Connection;

import jp.co.cuatro.dao.UsersDAO;
import jp.co.cuatro.dto.UsersDTO;
import jp.co.cuatro.util.ConnectionUtil;

public class LoginService {
	public UsersDTO execute(String userId, String password) throws Exception {

		String jndiName = "java:comp/env/jdbc/ecsite";

		try (Connection conn = ConnectionUtil.getConnection(jndiName)) {

			UsersDAO dao = new UsersDAO(conn);
			UsersDTO user = dao.findById(userId);

			if (user != null) {

				if (!password.equals(user.getPassword())) {
					user = null;
				}
			}

			return user;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("認証処理中にエラーが発生しました", e);
		}

	}
}
