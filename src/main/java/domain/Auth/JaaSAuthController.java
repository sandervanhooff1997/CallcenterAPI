package domain.Auth;

import javax.ejb.EJB;
import javax.ws.rs.*;

@Path("auth")
public class JaaSAuthController {

    @EJB
    private JaasAuthRepository repo;

    @GET
    @Produces("application/json")
    public User create() {
        User user = new User("s.vanhooff@hotmail.com", "123", "sander");
        return repo.create(user);
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public User getById(@PathParam("id") Long id) {
        return repo.getById(id);
    }
}
