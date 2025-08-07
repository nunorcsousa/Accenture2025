package io.altar.jseproject.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
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

import io.altar.jseproject.dtos.ShelfDTO;
import io.altar.jseproject.mappers.ShelfMapper;
import io.altar.jseproject.model.Shelf;
import io.altar.jseproject.services.ShelfService;

@Path("/shelves")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class ShelfController implements Serializable{
	
	private static final long serialVersionUID = 1149251039409861914L;
	
	public ShelfController(){};
	
	@Inject
	private ShelfService shelfService;

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
	public Response create(ShelfDTO dto) {
		Shelf shelf = ShelfMapper.fromDTO(dto);
		Long id = shelfService.create(shelf);
		return Response.status(Response.Status.CREATED).entity(id).build();
	}

	@GET
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<ShelfDTO> getAll() {
		List<ShelfDTO> result = new ArrayList<>();
		for (Shelf s : shelfService.getAll()) {
			result.add(ShelfMapper.toDTO(s));
		}
		return result;
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getById(@PathParam("id") long id) {
		Shelf shelf = shelfService.getById(id);
		if (shelf == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.ok(ShelfMapper.toDTO(shelf)).build();
	}

	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") long id, ShelfDTO dto) {
		Shelf existing = shelfService.getById(id);
		if (existing == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		Shelf updated = ShelfMapper.fromDTO(dto);
		updated.setId(id); // garantir consistência
		shelfService.update(updated);
		return Response.ok().build();
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("id") long id) {
		Shelf existing = shelfService.getById(id);
		if (existing == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		shelfService.delete(id);
		return Response.ok().build();
	}
}