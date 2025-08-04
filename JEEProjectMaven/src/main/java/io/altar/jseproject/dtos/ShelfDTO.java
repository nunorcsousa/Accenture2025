package io.altar.jseproject.dtos;

public class ShelfDTO {
	public long id;
    public int capacity;
    public double dailyPrice;
    public int currentQuantity;
    public long productId;

    public ShelfDTO() {} // Necessário para serialização/deserialização JSON

    public ShelfDTO(long id, int capacity, int currentQuantity, long productId, double dailyPrice) {
        this.id = id;
        this.capacity = capacity;
        this.dailyPrice = dailyPrice;
        this.currentQuantity = currentQuantity;
        this.productId = productId;
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public double getDailyPrice() {
		return dailyPrice;
	}

	public void setDailyPrice(double dailyPrice) {
		this.dailyPrice = dailyPrice;
	}

	public int getCurrentQuantity() {
		return currentQuantity;
	}

	public void setCurrentQuantity(int currentQuantity) {
		this.currentQuantity = currentQuantity;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

}