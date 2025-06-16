package jp.co.brmy.dto;

public class ItemsInCartDTO {

	private String userId;
	private int itemId;
	private int amount;
	private java.util.Date bookedDate;
	
	private ItemsDTO itemsDTO;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}



	public java.util.Date getBookedDate() {
		return bookedDate;
	}

	public void setBookedDate(java.util.Date bookedDate) {
		this.bookedDate = bookedDate;
	}

	public ItemsDTO getItemsDTO() {
		return itemsDTO;
	}

	public void setItemsDTO(ItemsDTO itemsDTO) {
		this.itemsDTO = itemsDTO;
	}
	
	
	
	
}
