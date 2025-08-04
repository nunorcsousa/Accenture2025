package io.altar.jseproject.mappers;

import io.altar.jseproject.dtos.ProductDTO;
import io.altar.jseproject.model.Product;
import io.altar.jseproject.model.Shelf;
import io.altar.jseproject.services.ShelfService;

public class ProductMapper {

    private static final ShelfService shelfService = new ShelfService();

    public static ProductDTO toDTO(Product p) {
        StringBuilder shelfSummary = new StringBuilder();
        if (p.getShelfIds() != null) {
            for (Long sid : p.getShelfIds()) {
                Shelf shelf = shelfService.getById(sid);
                if (shelf != null) {
                    shelfSummary.append("Shelf ")
                                .append(shelf.getId())
                                .append(" [")
                                .append(shelf.getCurrentQuantity())
                                .append("/")
                                .append(shelf.getCapacity())
                                .append("]; ");
                }
            }
        }

        return new ProductDTO(
            p.getId(),
            p.getName(),
            p.getPrice(),
            p.getDiscount(),
            p.getIva(),
            p.getQuantity(),
            p.getShelfIds(),
            shelfSummary.toString().trim()
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

