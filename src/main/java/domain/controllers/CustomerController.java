package domain.controllers;

import domain.models.Customer;
import domain.services.CustomerService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("customer")
public class CustomerController {

    @EJB
    private CustomerService service;

    @GET
    @Produces("application/json")
    public Response getAll() {
        return Response.ok(service.getAll()).build();
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getById(@PathParam("id") Long id) {
        Customer customer = service.getById(id);

        if (customer == null)
            return Response.status(Response.Status.NOT_FOUND).build();


        return Response.ok(customer).build();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response create(Customer customer) {
        if (!service.create(customer))
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();

        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Response update(Customer customer) {
        if(!service.update(customer))
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
