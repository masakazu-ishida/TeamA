package jp.co.brmy;

import java.sql.Connection;
import java.util.List;

import jp.co.brmy.dao.ItemsInCartDAO;
import jp.co.brmy.dto.ItemsInCartDTO;
import jp.co.brmy.util.CommonConstants;
import jp.co.brmy.util.ConnectionUtil;

public class CartDisplayService {

	public List<ItemsInCartDTO> cartItems(String userId) throws Exception {
		try (Connection conn = ConnectionUtil.getConnection(CommonConstants.LOOKUP_NAME)) {

			ItemsInCartDAO dao = new ItemsInCartDAO(conn);
			return dao.findByUserId(userId);

		}
	}

}
