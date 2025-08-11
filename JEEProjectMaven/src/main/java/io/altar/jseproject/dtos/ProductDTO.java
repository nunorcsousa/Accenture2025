package io.altar.jseproject.dtos;

import java.util.HashSet;
import java.util.Set;

import io.altar.jseproject.model.Product;

public class ProductDTO {

    private Long id;
    private String name;
    private double price;
    private double discount;
    private int iva;
    private int quantity;
    private Set<Long> shelves = new HashSet<>();

    public ProductDTO() {}

    public ProductDTO(Long id, String name, double price, double discount, int iva, int quantity, Set<Long> shelves) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.iva = iva;
        this.quantity = quantity;
        this.shelves = (shelves != null) ? new HashSet<>(shelves) : new HashSet<>();
    }

    // Conversão de Entity → DTO
    public static ProductDTO fromEntity(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getDiscount(),
                product.getIva(),
                product.getQuantity(),
                product.getShelves()
        );
    }

    // Conversão de DTO → Entity
    public Product toEntity() {
        Product product = new Product();
        product.setId(this.id);
        product.setName(this.name);
        product.setPrice(this.price);
        product.setDiscount(this.discount);
        product.setIva(this.iva);
        product.setQuantity(this.quantity);
        if (this.shelves != null) {
            product.setShelves(new HashSet<>(this.shelves));
        }
        return product;
    }

    // GETTERS & SETTERS
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

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Set<Long> getShelves() {
        return shelves;
    }
    public void setShelves(Set<Long> shelfIds) {
        this.shelves = shelves;
    }
}
