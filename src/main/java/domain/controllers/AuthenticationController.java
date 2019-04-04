package domain.controllers;

import domain.models.Auth.Code;
import domain.models.Auth.Employee;
import domain.models.Auth.LoginResponse;
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
    public Response login(Code code) {
        LoginResponse response = service.login(code);
        if (!response.isSuccess())
            return Response.status(Response.Status.UNAUTHORIZED).build();

        // true means no two factor is required on client side
        return Response.ok(response.getToken()).build();
    }

    @Path("/verify")
    @POST
    public Response verifyCode(Code code) {
        String token = service.verify(code);

        if (token == null)
            return Response.status(Response.Status.UNAUTHORIZED).build();

        return Response.ok(token).build();
    }
}
