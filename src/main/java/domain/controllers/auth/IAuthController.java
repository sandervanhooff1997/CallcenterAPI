package domain.controllers.auth;

import domain.models.Employee;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

public interface IAuthController {
    @POST
    @Path("register")
    @Consumes("application/json")
    @Produces("application/json")
    Response register(Employee employee);

    @POST
    @Path("login")
    @Consumes("application/json")
    @Produces("application/json")
    Response login(Employee employee);
}
