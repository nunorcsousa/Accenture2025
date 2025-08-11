package io.altar.jseproject.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private double price;
	private double discount;
	private int iva;
	private double pvp;
	private int quantity;

	@ElementCollection
    @CollectionTable(
        name = "product_shelves",
        joinColumns = @JoinColumn(name = "product_id")
    )
    @Column(name = "shelf_id")
    private Set<Long> shelves = new HashSet<>();

	public Product() {
	};

	public Product(String name, double price, int discount, int iva, int quantity, Set<Long> shelves) {
		this.name = name;
		this.price = price;
		this.discount = discount;
		this.iva = iva;
		this.pvp = calculatePVP();
		this.quantity = quantity;
		this.shelves = shelves;
	}

	private double calculatePVP() {
		double discountedPrice = price * (1 - discount / 100.0);
		return discountedPrice * (1 + iva / 100.0);
	}

	public void updatePVP() {
		this.pvp = calculatePVP();
	}

	@ElementCollection
    @CollectionTable(name = "product_shelves", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "shelf_id")
    private Set<Long> shelfIds = new HashSet<>();

    public void addShelfId(Long shelfId) {
        if (shelfIds == null) {
            shelfIds = new HashSet<>();
        }
        shelfIds.add(shelfId);
    }

    public void addShelfIds(Set<Long> shelfIds) {
        if (this.shelfIds == null) {
            this.shelfIds = new HashSet<>();
        }
        this.shelfIds.addAll(shelfIds);
    }

    public void removeShelfId(Long shelfId) {
        if (shelfIds != null) {
            shelfIds.remove(shelfId);
        }
    }
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public int getIva() {
		return iva;
	}

	public void setIva(int iva) {
		this.iva = iva;
	}

	public double getPvp() {
		return pvp;
	}

	public void setPvp(double pvp) {
		this.pvp = pvp;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Set<Long> getShelves() {
		return shelves;
	}

	public void setShelves(Set<Long> shelves) {
		this.shelves = shelves;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", discount=" + discount + ", iva=" + iva
				+ ", pvp=" + pvp + ", quantity=" + quantity + ", shelves=" + shelves + "]";
	}
}
