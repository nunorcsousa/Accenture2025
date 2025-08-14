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

import io.altar.jeeproject.controllers.dto.UserDTO;
import io.altar.jeeproject.model.User;
import io.altar.jeeproject.service.UserService;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

    @Inject
    private UserService service;

    @POST
    public Response create(User user) {
        Long id = service.create(user);
        user.setId(id);
        return Response.status(Response.Status.CREATED).entity(user).build();
    }

    @GET
    public List<UserDTO> list() {
        return service.getAll().stream().map(UserDTO::from).collect(Collectors.toList());
    }

    @GET @Path("/{id}")
    public User get(@PathParam("id") Long id) {
        User u = service.findById(id);
        if (u == null) throw new NotFoundException();
        return u;
    }

    @PUT @Path("/{id}")
    public Response update(@PathParam("id") Long id, User user) {
        if (service.findById(id) == null) throw new NotFoundException();
        user.setId(id);
        return Response.ok(service.update(user)).build();
    }

    @DELETE @Path("/{id}") 
    public Response delete(@PathParam("id") Long id) {
        service.delete(id);
        return Response.noContent().build();
    }
}
