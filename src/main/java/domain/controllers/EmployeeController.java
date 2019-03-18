package domain.controllers;

import domain.controllers.response.Response2;
import domain.models.Employee;
import domain.services.EmployeeService;

import javax.ejb.EJB;
import javax.ws.rs.*;

@Path("employee")
public class EmployeeController {

    @EJB
    private EmployeeService service;

    @GET
    @Produces("application/json")
    public Response2 getAll() {
        return new Response2(true, service.getAll());
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response2 getById(@PathParam("id") Long id) {
        Employee employee = service.getById(id);
        boolean success = employee != null;

        return new Response2(success, employee);
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response2 create(Employee employee) {
        boolean success = service.create(employee);

        return new Response2(success);
    }

    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Response2 update(Employee employee) {
        boolean success = service.update(employee);

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
