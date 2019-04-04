package domain.controllers;

import domain.models.Auth.Employee;
import domain.services.Auth.AuthenticationService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("authentication")
public class AuthenticationController {
    @EJB
    private AuthenticationService service;

    @Path("/register")
    @POST
    public Response register(Employee employee) {
        return Response.ok(service.register(employee)).build();
    }

    @Path("/login")
    @POST
    public Response login(Employee employee) {
        String token = service.login(employee.getEmail(), employee.getPassword());

        if (token == null)
            return Response.status(Response.Status.UNAUTHORIZED).build();

        return Response.ok(token).build();
    }
}
