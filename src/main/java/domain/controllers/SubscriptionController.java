package domain.controllers;

import domain.models.Subscription;
import domain.services.SubscriptionService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("subscription")
public class SubscriptionController {

    @EJB
    private SubscriptionService service;

    @GET
    @Produces("application/json")
    public Response getAll() {
        return Response.ok(service.getAll()).build();
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getById(@PathParam("id") Long id) {
        Subscription subscription = service.getById(id);

        if (subscription == null)
            return Response.status(Response.Status.NOT_FOUND).build();


        return Response.ok(subscription).build();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response create(Subscription subscription) {
        if (!service.create(subscription))
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();

        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Response update(Subscription subscription) {
        if(!service.update(subscription))
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
