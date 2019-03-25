package domain.controllers;

import domain.controllers.response.Response2;
import domain.models.Product;
import domain.services.ProductService;

import javax.ejb.EJB;
import javax.ws.rs.*;

@Path("product")
public class ProductController {

    @EJB
    private ProductService service;

    @GET
    @Produces("application/json")
    public Response2 getAll() {
        return new Response2(true, service.getAll());
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response2 getById(@PathParam("id") Long id) {
        Product product = service.getById(id);
        boolean success = product != null;

        return new Response2(success, product);
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response2 create(Product product) {
        boolean success = service.create(product);

        return new Response2(success);
    }

    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Response2 update(Product product) {
        boolean success = service.update(product);

        return new Response2(success);
    }

    @DELETE
    @Path("/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response2 delete(@PathParam("id") Long id) {
        boolean success = service.delete(id);

        return new Response2(success);
    }
}
