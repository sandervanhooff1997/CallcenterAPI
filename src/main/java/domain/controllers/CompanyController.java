package domain.controllers;

import domain.controllers.response.Response2;
import domain.models.Company;
import domain.services.CompanyService;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.*;

@Path("company")
@RolesAllowed(value = "users")
public class CompanyController {

    @EJB
    private CompanyService service;

    @GET
    @Produces("application/json")
    public Response2 getAll() {
        return new Response2(true, service.getAll());
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response2 getById(@PathParam("id") Long id) {
        Company company = service.getById(id);
        boolean success = company != null;

        return new Response2(success, company);
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response2 create(Company company) {
        boolean success = service.create(company);

        return new Response2(success);
    }

    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Response2 update(Company company) {
        boolean success = service.update(company);

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
