package domain.controllers;

import domain.controllers.response.Response2;
import domain.models.Customer;
import domain.services.CustomerService;

import javax.ejb.EJB;
import javax.ws.rs.*;

@Path("customer")
public class CustomerController {

    @EJB
    private CustomerService service;

    @GET
    @Produces("application/json")
    public Response2 getAll() {
        return new Response2(true, service.getAll());
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response2 getById(@PathParam("id") Long id) {
        Customer customer = service.getById(id);
        boolean success = customer != null;

        return new Response2(success, customer);
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response2 create(Customer customer) {
        boolean success = service.create(customer);

        return new Response2(success);
    }

    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Response2 update(Customer customer) {
        boolean success = service.update(customer);

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
