package domain.controllers;

import domain.models.Product;
import domain.models.Product;
import domain.services.ProductService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("product")
public class ProductController {

    @EJB
    private ProductService service;

    @GET
    @Produces("application/json")
    public Response getAll() {
        return Response.ok(service.getAll()).build();
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getById(@PathParam("id") Long id) {
        Product product = service.getById(id);

        if (product == null)
            return Response.status(Response.Status.NOT_FOUND).build();


        return Response.ok(product).build();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response create(Product product) {
        if (!service.create(product))
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();

        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Response update(Product product) {
        if(!service.update(product))
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();

        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path("/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response delete(@PathParam("id") Long id) {
        if (!service.delete(id))
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();

        return Response.status(Response.Status.OK).build();
    }
}
