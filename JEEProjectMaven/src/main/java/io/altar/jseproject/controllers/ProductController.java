package io.altar.jseproject.controllers;

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

import io.altar.jseproject.dtos.ProductDTO;
import io.altar.jseproject.model.Product;
import io.altar.jseproject.services.ProductService;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductController {

    @Inject
    private ProductService productService;

    @GET
    public List<ProductDTO> getAllProducts() {
        return productService.getAll().stream()
                .map(ProductDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @GET
    @Path("/{id}")
    public ProductDTO getProductById(@PathParam("id") Long id) {
        Product product = productService.getById(id);
        if (product == null) {
            throw new NotFoundException("Product not found");
        }
        return ProductDTO.fromEntity(product);
    }

    @POST
    public Response createProduct(ProductDTO dto) {
        Product product = dto.toEntity();
        productService.create(product);
        return Response.status(Response.Status.CREATED).entity(ProductDTO.fromEntity(product)).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateProduct(@PathParam("id") Long id, ProductDTO dto) {
        Product existing = productService.getById(id);
        if (existing == null) {
            throw new NotFoundException("Product not found");
        }

        existing.setName(dto.getName());
        existing.setPrice(dto.getPrice());
        existing.setDiscount(dto.getDiscount());
        existing.setIva(dto.getIva());
        existing.setQuantity(dto.getQuantity());

        // Atualiza shelves
        if (dto.getShelfIds() != null) {
            existing.setShelves(dto.getShelfIds());
        }

        productService.update(existing);
        return Response.ok(ProductDTO.fromEntity(existing)).build();
    }

    @Path("/{id}/shelves")
    public Response addShelvesToProduct(@PathParam("id") Long id, List<Long> shelfIds) {
        Product existing = productService.getById(id);
        if (existing == null) {
            throw new NotFoundException("Product not found");
        }

        existing.addShelfIds(new java.util.HashSet<>(shelfIds));
        productService.update(existing);

        return Response.ok(ProductDTO.fromEntity(existing)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteProduct(@PathParam("id") Long id) {
        productService.delete(id);
        return Response.noContent().build();
    }
}
