package io.altar.jseproject.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import io.altar.jseproject.dtos.StoreDTO;
import io.altar.jseproject.model.Store;
import io.altar.jseproject.services.StoreService;

@Path("/Stores")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StoreController {
	private StoreService storeService = new StoreService();

	@Context
	protected UriInfo context;

	@GET
	@Path("status")
	@Produces(MediaType.TEXT_PLAIN)
	public String status() {
		return "Url : " + context.getRequestUri().toString() + " is Ok";
	}

	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(StoreDTO dto) {
		Store Store = fromDTO(dto);
		Long id = storeService.create(Store);
		return Response.status(Response.Status.CREATED).entity(id).build();
	}

	@GET
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<StoreDTO> getAll() {
		List<StoreDTO> result = new ArrayList<>();
		for (Store s : storeService.getAll()) {
			result.add(toDTO(s));
		}
		return result;
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getById(@PathParam("id") long id) {
		Store Store = storeService.getById(id);
		if (Store == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.ok(toDTO(Store)).build();
	}

	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") long id, StoreDTO dto) {
		Store existing = storeService.getById(id);
		if (existing == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		Store updated = fromDTO(dto);
		updated.setId(id); // garantir consistência
		storeService.update(updated);
		return Response.ok().build();
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("id") long id) {
		Store existing = storeService.getById(id);
		if (existing == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		storeService.delete(id);
		return Response.ok().build();
	}

    private StoreDTO toDTO(Store u) {
        StoreDTO dto = new StoreDTO();
        dto.id = u.getId();
        dto.name = u.getName();
        dto.userIds = u.getUserIds();
        return dto;
    }

    private Store fromDTO(StoreDTO dto) {
        Store u = new Store();
        u.setId(dto.id);
        u.setName(dto.name);
        u.setUserIds(dto.userIds);
        return u;
    }
}
