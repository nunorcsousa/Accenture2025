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

import io.altar.jeeproject.controllers.dto.ProductDTO;
import io.altar.jeeproject.model.Product;
import io.altar.jeeproject.service.ProductService;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductController {

    @Inject
    private ProductService service;

    @POST
    public Response create(ProductDTO dto) {
        Product p = dto.toEntity();
        Long id = service.create(p);
        p.setId(id);
        return Response.status(Response.Status.CREATED).entity(ProductDTO.from(p)).build();
    }

    @GET
    public List<ProductDTO> list() {
        return service.getAll().stream().map(ProductDTO::from).collect(Collectors.toList());
    }

    @GET @Path("/{id}")
    public ProductDTO get(@PathParam("id") Long id) {
        Product p = service.findById(id);
        if (p == null) throw new NotFoundException();
        return ProductDTO.from(p);
    }

    @PUT @Path("/{id}")
    public Response update(@PathParam("id") Long id, ProductDTO dto) {
        Product existing = service.findById(id);
        if (existing == null) throw new NotFoundException();
        Product updated = dto.toEntity();
        updated.setId(id);
        service.update(updated);
        return Response.ok(ProductDTO.from(updated)).build();
    }

    @DELETE @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        service.delete(id);
        return Response.noContent().build();
    }
}
