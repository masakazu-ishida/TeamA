package jp.co.cuatro.dao;

import java.sql.Connection;

public class PurchasesDAO {
	private Connection con;

	public PurchasesDAO(Connection con) {
		this.con = con;
	}
}