package io.altar.jseproject.services;

import java.util.Collection;

import io.altar.jseproject.model.Shelf;
import io.altar.jseproject.repositories.ShelfRepository;

public class ShelfService extends EntityService{
	
	private ShelfRepository shelfRepo = ShelfRepository.getInstance();

    public Long create(Shelf entity) {
        return shelfRepo.create(entity);
    }

    public Shelf getById(Long id) {
        return shelfRepo.getById(id);
    }

    public Collection<Shelf> getAll() {
        return shelfRepo.getAll();
    }

    public void update(Shelf entity) {
        shelfRepo.update(entity);
    }

    public void delete(Long id) {
        shelfRepo.delete(id);
    }
    
}
