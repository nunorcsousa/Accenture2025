package io.altar.jeeproject.controllers;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.altar.jeeproject.model.Shelf;
import io.altar.jeeproject.service.ShelfService;

@Path("/shelves")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ShelfController {

    @Inject
    private ShelfService service;

    @POST
    public Response create(Shelf shelf) {
        Long id = service.create(shelf);
        shelf.setId(id);
        return Response.status(Response.Status.CREATED).entity(shelf).build();
    }

    @GET
    public List<Shelf> list() { return service.getAll(); }

    @GET @Path("/{id}")
    public Shelf get(@PathParam("id") Long id) {
        Shelf s = service.findById(id);
        if (s == null) throw new NotFoundException();
        return s;
    }

    @PUT @Path("/{id}")
    public Response update(@PathParam("id") Long id, Shelf shelf) {
        Shelf existing = service.findById(id);
        if (existing == null) throw new NotFoundException();
        shelf.setId(id);
        return Response.ok(service.update(shelf)).build();
    }

    @DELETE @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        service.delete(id);
        return Response.noContent().build();
    }
}
