package domain.controllers;

import com.google.gson.Gson;
import domain.controllers.response.Response2;
import domain.models.Call;
import domain.services.CallService;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

@Path("call")
public class CallController {

    @EJB
    private CallService service;

    @GET
    @Produces("application/json")
    public Response getAll(@Context HttpServletRequest request) {
        Gson gson = new Gson();

        String json = gson.toJson(service.getAll());
try {
    return Response.ok().build();
} catch (Exception e) {
    System.out.println(e.getMessage());
    return Response.ok().build();
}
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response2 getById(@PathParam("id") Long id) {
        Call call = service.getById(id);
        boolean success = call != null;

        return new Response2(success, call);
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response2 create(Call call) {
        boolean success = service.create(call);

        return new Response2(success);
    }

    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Response2 update(Call call) {
        boolean success = service.update(call);

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
