package jp.co.cuatro.dto;

public class CartDTO {
	private String itemId;
	private int amount;
	private String bookedDate;
	private String name;
	private String manufacturer;
	private String color;
	private int price;
	private int total;

	//コンストラクタ、Getter、Setter

	public CartDTO() {

	}

	public CartDTO(String name, String color, String manufacturer, int price, int amount, String itemId, int total) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.name = name;
		this.color = color;
		this.manufacturer = manufacturer;
		this.price = price;
		this.amount = amount;
		this.itemId = itemId;
		this.total = total;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

}
