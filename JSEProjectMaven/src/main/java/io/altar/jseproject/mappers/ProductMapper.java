package io.altar.jseproject.mappers;

import java.util.HashSet;

import javax.enterprise.context.ApplicationScoped;

import io.altar.jseproject.dtos.ProductDTO;
import io.altar.jseproject.model.Product;

@ApplicationScoped
public class ProductMapper {

    public ProductDTO toDTO(Product entity) {
        ProductDTO dto = new ProductDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        dto.setShelves(entity.getShelves());
        return dto;
    }

    public Product toEntity(ProductDTO dto) {
        Product entity = new Product();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setShelves(new HashSet<Long>(dto.getShelves()));
        return entity;
    }
}
