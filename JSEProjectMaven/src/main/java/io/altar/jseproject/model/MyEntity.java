package io.altar.jseproject.model;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public abstract class MyEntity {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
    	if (id == null) throw new IllegalArgumentException();
        this.id = id;
    }
}
