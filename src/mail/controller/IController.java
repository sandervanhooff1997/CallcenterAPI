package domain.controllers;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

public interface IController<T> {
    @GET
    @Produces("application/json")
    @Consumes("application/json")
    Response getAll();

    @GET
    @Path("/{id}")
    @Produces("application/json")
    @Consumes("application/json")
    Response getById(@PathParam("id") Long id);

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    Response save(T entity);

    @PUT
    @Path("/{id}")
    @Produces("application/json")
    @Consumes("application/json")
    Response update(@PathParam("id") Long id, T entity);

    @DELETE
    @Produces("application/json")
    @Consumes("application/json")
    Response delete(Long id);
}
