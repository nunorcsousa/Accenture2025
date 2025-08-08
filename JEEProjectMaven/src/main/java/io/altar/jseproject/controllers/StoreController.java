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

import io.altar.jseproject.model.Store;
import io.altar.jseproject.repositories.StoreRepository;
import jakarta.transaction.Transactional;

@Path("/stores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StoreController {

    @Inject
    StoreRepository storeRepository;

    @GET
    public List<Store> getAll() {
        return storeRepository.findAll();
    }

    @GET
    @Path("/{id}")
    public Store getById(@PathParam("id") Long id) {
        return storeRepository.findById(id);
    }

    @POST
    @Transactional
    public Response create(Store store) {
        storeRepository.save(store);
        return Response.status(Response.Status.CREATED).entity(store).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response update(@PathParam("id") Long id, Store store) {
        store.setId(id);
        storeRepository.update(store);
        return Response.ok(store).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        storeRepository.delete(id);
        return Response.noContent().build();
    }
}
