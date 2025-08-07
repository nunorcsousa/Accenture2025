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

import io.altar.jseproject.dtos.ProductDTO;
import io.altar.jseproject.mappers.ProductMapper;
import io.altar.jseproject.model.Product;
import io.altar.jseproject.services.ProductService;

@Path("products")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class ProductController implements Serializable{
	
	private static final long serialVersionUID = 1149251039409861914L;
	
	public ProductController(){};
	
	@Inject
	private ProductService productService;

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
	public Response create(ProductDTO dto) {
		Product product = ProductMapper.fromDTO(dto);
		Long id = productService.create(product);
		return Response.status(Response.Status.CREATED).entity(id).build();
	}

	@GET
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<ProductDTO> getAll() {
		List<ProductDTO> result = new ArrayList<>();
		for (Product p : productService.getAll()) {
			result.add(ProductMapper.toDTO(p));
		}
		return result;
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getById(@PathParam("id") long id) {
		Product product = productService.getById(id);
		if (product == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.ok(ProductMapper.toDTO(product)).build();
	}

	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") long id, ProductDTO dto) {
		Product existing = productService.getById(id);
		if (existing == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		Product updated = ProductMapper.fromDTO(dto);
		updated.setId(id); // forçar consistência
		productService.update(updated);
		return Response.ok().build();
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("id") long id) {
		Product product = productService.getById(id);
		if (product == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		productService.delete(id);
		return Response.ok().build();
	}

}