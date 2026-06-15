package jp.co.cuatro.dao;

import java.sql.Connection;

public class PurchaseDetailsDAO {
	private Connection con;

	public PurchaseDetailsDAO(Connection con) {
		this.con = con;
	}
}