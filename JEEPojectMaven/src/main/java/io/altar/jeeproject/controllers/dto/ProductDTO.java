package io.altar.jeeproject.controllers.dto;

import java.util.List;

import io.altar.jeeproject.model.Product;

public class ProductDTO {
    public Long id;
    public String name;
    public double price;
    public int discount;
    public int iva;
    public int quantity;
    public List<Long> shelfIds;

    public static ProductDTO from(Product p) {
        ProductDTO d = new ProductDTO();
        d.id = p.getId();
        d.name = p.getName();
        d.price = p.getPrice();
        d.discount = p.getDiscount();
        d.iva = p.getIva();
        d.quantity = p.getQuantity();
        d.shelfIds = p.getShelves().stream().collect(java.util.stream.Collectors.toList());
        return d;
    }

    public Product toEntity() {
        Product p = new Product();
        p.setId(id);
        p.setName(name);
        p.setPrice(price);
        p.setDiscount(discount);
        p.setIva(iva);
        p.setQuantity(quantity);
        if (shelfIds != null) {
            p.setShelves(new java.util.HashSet<>(shelfIds));
        }
        return p;
    }
}
