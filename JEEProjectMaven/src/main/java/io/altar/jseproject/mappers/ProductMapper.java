package io.altar.jseproject.mappers;

import io.altar.jseproject.dtos.ProductDTO;
import io.altar.jseproject.model.Product;

public class ProductMapper {

    public static ProductDTO toDTO(Product p) {
        return new ProductDTO(
            p.getId(),
            p.getName(),
            p.getPrice(),
            p.getDiscount(),
            p.getIva(),
            p.getQuantity(),
            p.getShelfIds()
        );
    }

    public static Product fromDTO(ProductDTO dto) {
        Product p = new Product();
        p.setName(dto.name);
        p.setPrice(dto.price);
        p.setDiscount(dto.discount);
        p.setIva(dto.iva);
        p.setQuantity(dto.quantity);
        p.setShelfIds(dto.shelfIds);
        p.setId(dto.id);
        return p;
    }
}

