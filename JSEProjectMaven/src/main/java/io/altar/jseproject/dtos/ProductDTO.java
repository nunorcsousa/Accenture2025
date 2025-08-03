package io.altar.jseproject.dtos;

import java.util.Set;

public class ProductDTO {
	public long id;
    public String name;
    public double price;
    public int discount;
    public int iva;
    public int quantity;
    public Set<Long> shelfIds;
    public String shelfSummary;

    public ProductDTO() {} // Required for deserialization

    public ProductDTO(long id, String name, double price, int discount, int iva, int quantity,
                      Set<Long> shelfIds, String shelfSummary) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.iva = iva;
        this.quantity = quantity;
        this.shelfIds = shelfIds;
        this.shelfSummary = shelfSummary;
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Set<Long> getShelfIds() {
		return shelfIds;
	}

	public void setShelfId(Set<Long> shelfIds) {
		this.shelfIds = shelfIds;
	}

	public String getShelfSummary() {
		return shelfSummary;
	}

	public void setShelfSummary(String shelfSummary) {
		this.shelfSummary = shelfSummary;
	}
	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public int getIva() {
		return iva;
	}

	public void setIva(int iva) {
		this.iva = iva;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setShelfIds(Set<Long> shelfIds) {
		this.shelfIds = shelfIds;
	}
}