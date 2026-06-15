package jp.co.cuatro.dto;

public class PurchasesDTO {
	private int purchaseId;
	private String purchasedUser;
	private String purchasedDate;
	private String destination;
	private boolean cancel;

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
