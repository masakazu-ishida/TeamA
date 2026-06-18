package jp.co.cuatro.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.cuatro.dto.PurchasesDTO;
import jp.co.cuatro.util.TestBase;

class PurchasesCancelConfirmationServiceTest extends TestBase {

	@BeforeEach
	void setUp() throws Exception {
		String sqlFilePath = "/init_data.sql";
		super.initSQLFiles(sqlFilePath);
	}

	@Test
	void testExecute() throws Exception {

		PurchasesCancelConfirmationService cancelConfirmation = new PurchasesCancelConfirmationService();

		PurchasesDTO result = cancelConfirmation.execute(1);
		assertNotNull(result);

	}

	@Test
	void testExecute_NotId() throws Exception {

		PurchasesCancelConfirmationService cancelConfirmation = new PurchasesCancelConfirmationService();

		PurchasesDTO result = cancelConfirmation.execute(4);
		assertNull(result);

	}

}
