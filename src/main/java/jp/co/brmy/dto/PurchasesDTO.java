package jp.co.brmy.dto;

import java.util.List;

public class PurchasesDTO {

	private int purchaseId;
	private String purchaseUser;
	private java.util.Date purchaseDate;
	private String destination;
	private boolean cansel;
	private List<PurchaseDetailsDTO> purchaseDetailsDTO;

	public List<PurchaseDetailsDTO> getPurchaseDetailsDTO() {
		return purchaseDetailsDTO;
	}

	public void setPurchaseDetailsDTO(List<PurchaseDetailsDTO> purchaseDetailsDTO) {
		this.purchaseDetailsDTO = purchaseDetailsDTO;
	}

	public int getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(int purchaseId) {
		this.purchaseId = purchaseId;
	}

	public String getPurchaseUser() {
		return purchaseUser;
	}

	public void setPurchaseUser(String purchaseUser) {
		this.purchaseUser = purchaseUser;
	}

	public java.util.Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(java.util.Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public boolean getCansel() {
		return cansel;
	}

	public void setCansel(boolean cansel) {
		this.cansel = cansel;
	}

}
