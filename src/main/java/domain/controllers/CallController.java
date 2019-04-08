package domain.controllers;

import domain.models.Call;
import domain.models.Link;
import domain.services.CallService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("call")
public class CallController {

    @EJB
    private CallService service;

    @GET
    @Produces("application/json")
    public Response getAll() {
        return Response.ok(service.getAll()).build();
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getById(@PathParam("id") Long id, @Context UriInfo uriInfo) {
        Call call = service.getById(id);

        if (call == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        call.getLinks().add(new Link(getUriForSelf(uriInfo, call), "Self", "GET"));
        call.getLinks().add(new Link(getUriForSelf(uriInfo, call), "Self", "PUT"));
        call.getLinks().add(new Link(getUriForSelf(uriInfo, call), "Self", "DELETE"));

        return Response.ok(call).build();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response create(Call call) {
        if (!service.create(call))
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();

        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Response update(Call call) {
        if(!service.update(call))
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

    private String getUriForSelf (UriInfo uriInfo, Call c) {
        return uriInfo.getBaseUriBuilder()
                .path(CallController.class)
                .path(Long.toString(c.getId()))
                .build()
                .toString();
    }
}
