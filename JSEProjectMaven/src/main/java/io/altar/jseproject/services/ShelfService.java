package io.altar.jseproject.services;

import java.util.Collection;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import io.altar.jseproject.model.Shelf;
import io.altar.jseproject.repositories.ShelfRepository;

@RequestScoped
public class ShelfService extends EntityService {

	@Inject
	private ShelfRepository shelfRepo;

	public ShelfService() {
	};

	public Long create(Shelf entity) {
		return shelfRepo.save(entity);
	}

	public Shelf getById(Long id) {
		return shelfRepo.findById(id);
	}

	public Collection<Shelf> getAll() {
		return shelfRepo.findAll();
	}

	public void update(Shelf entity) {
		shelfRepo.update(entity);
	}

	public void delete(Long id) {
		shelfRepo.delete(id);
	}

}
