package io.altar.jseproject.model;

public class Shelf extends MyEntity {
	private int capacity;
	private int used;
    private int currentQuantity;
    private Long productId; // Apenas um produto por prateleira
    private double dailyPrice;

    public Shelf(int capacity, double dailyPrice) {
        this.capacity = capacity;
        this.currentQuantity = 0;
        this.dailyPrice = dailyPrice;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getUsed() {
        return used;
    }

    public int getAvailableSpace() {
        return capacity - used;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setUsed(int used) {
        this.used = used;
    }

    public void addQuantity(int quantity) {
        this.used += quantity;
    }

    public void removeQuantity(int quantity) {
        this.used -= quantity;
    }

	public int getCurrentQuantity() {
		return currentQuantity;
	}

	public void setCurrentQuantity(int currentQuantity) {
		this.currentQuantity = currentQuantity;
	}

    public boolean hasSpace(int quantity) {
        return currentQuantity + quantity <= capacity;
    }
    
    public Long getProductId() { 
    	return productId; 
    }
    
    public void setProductId(Long productId) { 
    	this.productId = productId; 
    }
    
	@Override
	public String toString() {
		return "Shelf [capacity=" + capacity + ", used=" + used + ", currentQuantity=" + currentQuantity
				+  ", productId=" + productId + "]";
	}

	public double getDailyPrice() {
		return dailyPrice;
	}

	public void setDailyPrice(double dailyPrice) {
		this.dailyPrice = dailyPrice;
	}
    
    
}
