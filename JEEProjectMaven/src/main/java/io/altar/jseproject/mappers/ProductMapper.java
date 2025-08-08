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
            p.getShelves()
        );
    }

    public static Product fromDTO(ProductDTO dto) {
        Product p = new Product();
        p.setName(dto.getName());
        p.setPrice(dto.getPrice());
        p.setDiscount(dto.getDiscount());
        p.setIva(dto.getIva());
        p.setQuantity(dto.getQuantity());
        p.setShelves(dto.getShelfIds());
        p.setId(dto.getId());
        return p;
    }
}

