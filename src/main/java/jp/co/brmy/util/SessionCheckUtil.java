package jp.co.brmy.util;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionCheckUtil {

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

	public static boolean isValid(HttpServletRequest request) {

		HttpSession session = request.getSession(false);
		if (session == null) {
			return false;
		} else {
			if (session.getAttribute("id") == null) {
				return false;
			}
			return true;
		}

	}

}
