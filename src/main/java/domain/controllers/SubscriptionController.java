package domain.controllers;

import domain.controllers.response.Response2;
import domain.models.Subscription;
import domain.services.SubscriptionService;

import javax.ejb.EJB;
import javax.ws.rs.*;

@Path("subscription")
public class SubscriptionController {

    @EJB
    private SubscriptionService service;

    @GET
    @Produces("application/json")
    public Response2 getAll() {
        return new Response2(true, service.getAll());
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response2 getById(@PathParam("id") Long id) {
        Subscription subscription = service.getById(id);
        boolean success = subscription != null;

        return new Response2(success, subscription);
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response2 create(Subscription subscription) {
        boolean success = service.create(subscription);

        return new Response2(success);
    }

    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Response2 update(Subscription subscription) {
        boolean success = service.update(subscription);

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
