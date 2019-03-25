package domain.controllers.auth;

import domain.models.Employee;
import domain.services.auth.IAuthService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("auth")
public class AuthController implements IAuthController {
    @EJB
    private IAuthService service;

    @Override
    public Response register(Employee employee) {
        return Response.ok(service.register(employee)).build();
    }

    @Override
    public Response login(Employee employee) {
        return Response.ok(service.login(employee.getEmail(), employee.getPassword())).build();
    }
}
