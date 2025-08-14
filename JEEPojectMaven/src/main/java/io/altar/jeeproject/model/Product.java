package io.altar.jeeproject.model;

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
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String name;

    private double price;
    private int discount;
    private int iva;
    private int quantity;

    @ElementCollection
    @CollectionTable(name = "product_shelves", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "shelf_id")
    private Set<Long> shelves = new HashSet<>();

    public Product() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public int getDiscount() { return discount; }
    public void setDiscount(int discount) { this.discount = discount; }
    public int getIva() { return iva; }
    public void setIva(int iva) { this.iva = iva; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public Set<Long> getShelves() { return shelves; }
    public void setShelves(Set<Long> shelves) { this.shelves = shelves; }

    public void addShelfId(Long shelfId) { this.shelves.add(shelfId); }
    public void addShelfIds(Set<Long> ids) { if (ids != null) this.shelves.addAll(ids); }
    public void removeShelfId(Long shelfId) { this.shelves.remove(shelfId); }
}
