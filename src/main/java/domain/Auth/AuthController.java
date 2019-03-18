package domain.Auth;

import domain.controllers.response.Response2;

import javax.ejb.EJB;
import javax.ws.rs.*;

@Path("auth")
public class AuthController {

    @EJB
    private UserEJB repo;

    @GET
    @Consumes("application/json")
    @Produces("application/json")
    public Response2 create() {
        User u = new User("s.vanhooff@hotmail.com", "123", "sander");
        repo.createUser(u);

        return new Response2(true);
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response2 getById(@PathParam("id") Long id) {
        User u = repo.findUserById(id);
        boolean success = u != null;

        return new Response2(success, u);
    }
}
