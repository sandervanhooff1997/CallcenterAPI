package domain.controllers;

import domain.models.Auth.Employee;
import domain.services.EmployeeService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("employee")
public class EmployeeController {

    @EJB
    private EmployeeService service;

    @GET
    @Produces("application/json")
    public Response getAll() {
        return Response.ok(service.getAll()).build();
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getById(@PathParam("id") Long id) {
        Employee employee = service.getById(id);

        if (employee == null)
            return Response.status(Response.Status.NOT_FOUND).build();


        return Response.ok(employee).build();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response create(Employee employee) {
        if (!service.create(employee))
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();

        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Response update(Employee employee) {
        if(!service.update(employee))
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
