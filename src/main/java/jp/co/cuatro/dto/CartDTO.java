package jp.co.cuatro.dto;

import java.time.LocalDate;

public class CartDTO {
	private String userId;
	private int itemId;
	private int amount;
	private LocalDate bookedDate;
	private ItemDTO Item;
	private int total;

	//コンストラクタ、Getter、Setter

	public CartDTO() {

	}

	public CartDTO(String userId, int itemId, int amount, LocalDate bookedDate, int total, ItemDTO Item) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.userId = userId;
		this.itemId = itemId;
		this.amount = amount;
		this.bookedDate = bookedDate;
		this.total = total;
		this.Item = Item;
	}

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

	public LocalDate getBookedDate() {
		return bookedDate;
	}

	public void setBookedDate(LocalDate bookedDate) {
		this.bookedDate = bookedDate;
	}

	public ItemDTO getItem() {
		return Item;
	}

	public void setItem(ItemDTO item) {
		Item = item;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

}
