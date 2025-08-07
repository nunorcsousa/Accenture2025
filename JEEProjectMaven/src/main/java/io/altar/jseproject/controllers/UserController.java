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

import io.altar.jseproject.dtos.UserDTO;
import io.altar.jseproject.model.User;
import io.altar.jseproject.services.UserService;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class UserController implements Serializable{
	
	private static final long serialVersionUID = 1149251039409861914L;
	
	@Inject
	private UserService userService;
	
	public UserController() {};

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
	public Response create(UserDTO dto) {
		User User = fromDTO(dto);
		Long id = userService.create(User);
		return Response.status(Response.Status.CREATED).entity(id).build();
	}

	@GET
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<UserDTO> getAll() {
		List<UserDTO> result = new ArrayList<>();
		for (User s : userService.getAll()) {
			result.add(toDTO(s));
		}
		return result;
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getById(@PathParam("id") long id) {
		User User = userService.getById(id);
		if (User == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.ok(toDTO(User)).build();
	}

	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") long id, UserDTO dto) {
		User existing = userService.getById(id);
		if (existing == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		User updated = fromDTO(dto);
		updated.setId(id); // garantir consistência
		userService.update(updated);
		return Response.ok().build();
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("id") long id) {
		User existing = userService.getById(id);
		if (existing == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		userService.delete(id);
		return Response.ok().build();
	}

    private UserDTO toDTO(User u) {
        UserDTO dto = new UserDTO();
        dto.id = u.getId();
        dto.name = u.getName();
        dto.username = u.getUsername();
        dto.password = u.getPassword();
        dto.storeIds = u.getStoreIds();
        return dto;
    }

    private User fromDTO(UserDTO dto) {
        User u = new User();
        u.setId(dto.id);
        u.setName(dto.name);
        u.setUsername(dto.username);
        u.setPassword(dto.password);
        u.setStoreIds(dto.storeIds);
        return u;
    }
}
