package jp.co.cuatro.dto;

import java.util.ArrayList;
import java.util.List;

public class PurchasesDTO {
	private int purchaseId;
	private String purchasedUser;
	private String purchasedDate;
	private String destination;
	private boolean cancel;

	private List<PurchaseDetailsDTO> detailsList = new ArrayList<>();

	public List<PurchaseDetailsDTO> getDetailsList() {
		return detailsList;
	}

	public void setDetailsList(List<PurchaseDetailsDTO> detailsList) {
		this.detailsList = detailsList;
	}

	public int getPurchaseId() {
		return purchaseId;

	}

	public void setPurchaseId(int purchaseId) {
		this.purchaseId = purchaseId;
	}

	public String getPurchasedUser() {
		return purchasedUser;
	}

	public void setPurchasedUser(String purchasedUser) {
		this.purchasedUser = purchasedUser;
	}

	public String getPurchasedDate() {
		return purchasedDate;
	}

	public void setPurchasedDate(String purchasedDate) {
		this.purchasedDate = purchasedDate;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public boolean isCancel() {
		return cancel;
	}

	public void setCancel(boolean cancel) {
		this.cancel = cancel;
	}

}
