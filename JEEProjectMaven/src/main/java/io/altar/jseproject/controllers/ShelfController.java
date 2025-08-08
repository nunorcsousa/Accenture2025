package io.altar.jseproject.controllers;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.altar.jseproject.model.Shelf;
import io.altar.jseproject.repositories.ShelfRepository;
import jakarta.transaction.Transactional;

@Path("/shelves")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ShelfController {

    @Inject
    ShelfRepository shelfRepository;

    @GET
    public List<Shelf> getAll() {
        return shelfRepository.findAll();
    }

    @GET
    @Path("/{id}")
    public Shelf getById(@PathParam("id") Long id) {
        return shelfRepository.findById(id);
    }

    @POST
    @Transactional
    public Response create(Shelf shelf) {
        shelfRepository.save(shelf);
        return Response.status(Response.Status.CREATED).entity(shelf).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response update(@PathParam("id") Long id, Shelf shelf) {
        shelf.setId(id);
        shelfRepository.update(shelf);
        return Response.ok(shelf).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        shelfRepository.delete(id);
        return Response.noContent().build();
    }
}
