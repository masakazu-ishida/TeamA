//package jp.co.cuatro.service;
//
//import java.sql.Connection;
//import java.util.ArrayList;
//import java.util.List;
//
//import jp.co.cuatro.dao.CartDAO;
//import jp.co.cuatro.dto.CartDTO;
//import jp.co.cuatro.util.ConnectionUtil;
//
//public class ConfirmationService {
//	String jndiName = "java:comp/env/jdbc/ecsite";
//
//	public List<CartDTO> findUserId(String userId) {
//		List<CartDTO> userBox = new ArrayList<CartDTO>();
//
//		try (Connection con = ConnectionUtil.getConnection(jndiName)) {
//			CartDAO dao = new CartDAO(con);
//			userBox = dao.findByUserId(userId);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//
//		}
//		return userBox;
//	}
//}
