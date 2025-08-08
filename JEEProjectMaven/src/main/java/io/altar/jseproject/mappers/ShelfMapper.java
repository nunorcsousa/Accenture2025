package io.altar.jseproject.mappers;

import io.altar.jseproject.dtos.ShelfDTO;
import io.altar.jseproject.model.Shelf;

public class ShelfMapper {

    public static ShelfDTO toDTO(Shelf shelf) {
        return new ShelfDTO(
            shelf.getId(),
            shelf.getCapacity(),
            shelf.getCurrentQuantity(),
            shelf.getProductId(),
            shelf.getDailyPrice()
        );
    }

    public static Shelf fromDTO(ShelfDTO dto) {
        Shelf shelf = new Shelf();
        shelf.setId(dto.getId());
        shelf.setCapacity(dto.getCapacity());
        shelf.setCurrentQuantity(dto.getCurrentQuantity());
        shelf.setProductId(dto.getProductId());
        shelf.setDailyPrice(dto.getDailyPrice());
        return shelf;
    }
}

