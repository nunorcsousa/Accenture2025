package io.altar.jseproject.services;

import java.io.Serializable;
import java.util.Collection;

import javax.enterprise.context.RequestScoped;

import io.altar.jseproject.model.Shelf;
import io.altar.jseproject.repositories.ShelfRepository;

@RequestScoped
public class ShelfService extends EntityService implements Serializable{
	
	private static final long serialVersionUID = 1149251039409861914L;
	
	private ShelfRepository shelfRepo;
	
	public ShelfService() {};
	
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
