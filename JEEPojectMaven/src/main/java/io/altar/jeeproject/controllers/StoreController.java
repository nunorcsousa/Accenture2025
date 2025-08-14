package io.altar.jeeproject.controllers;

import java.util.List;
import java.util.stream.Collectors;

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

import io.altar.jeeproject.controllers.dto.StoreDTO;
import io.altar.jeeproject.model.Store;
import io.altar.jeeproject.service.StoreService;

@Path("/stores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StoreController {

    @Inject
    private StoreService service;

    @POST
    public Response create(Store store) {
        Long id = service.create(store);
        store.setId(id);
        return Response.status(Response.Status.CREATED).entity(store).build();
    }

    @GET
    public List<StoreDTO> list() {
        return service.getAll().stream().map(StoreDTO::from).collect(Collectors.toList());
    }

    @GET @Path("/{id}")
    public Store get(@PathParam("id") Long id) {
        Store s = service.findById(id);
        if (s == null) throw new NotFoundException();
        return s;
    }

    @PUT @Path("/{id}")
    public Response update(@PathParam("id") Long id, Store store) {
        if (service.findById(id) == null) throw new NotFoundException();
        store.setId(id);
        return Response.ok(service.update(store)).build();
    }

    @DELETE @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        service.delete(id);
        return Response.noContent().build();
    }
}
