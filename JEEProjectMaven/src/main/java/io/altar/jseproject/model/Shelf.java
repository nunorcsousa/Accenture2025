package io.altar.jseproject.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//public class Shelf extends MyEntity{

@Entity
@Table(name = "shelves")
public class Shelf implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private int capacity;
	private int currentQuantity;
	private double dailyPrice;
	public long productId;

	@ManyToMany(mappedBy = "shelves")
	private Set<Product> products = new HashSet<>();

	@ManyToOne
	@JoinColumn(name = "store_id")
	private Store store;

	public Shelf() {
	};

	public Shelf(int capacity, double dailyPrice) {
		this.capacity = capacity;
		this.currentQuantity = 0;
		this.dailyPrice = dailyPrice;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getCurrentQuantity() {
		return currentQuantity;
	}

	public void setCurrentQuantity(int currentQuantity) {
		this.currentQuantity = currentQuantity;
	}

	public double getDailyPrice() {
		return dailyPrice;
	}

	public void setDailyPrice(double dailyPrice) {
		this.dailyPrice = dailyPrice;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}
	
	public boolean hasSpace(int quantityToAdd) {
	    return (currentQuantity + quantityToAdd) <= capacity;
	}

	@Override
	public String toString() {
		return "Shelf [id=" + id + ", capacity=" + capacity + ", currentQuantity=" + currentQuantity + ", dailyPrice="
				+ dailyPrice + ", store=" + store + "]";
	}

}
