package domain.controllers;

import domain.models.Company;
import domain.services.CompanyService;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("company")
@RolesAllowed(value = "users")
public class CompanyController {

    @EJB
    private CompanyService service;

    @GET
    @Produces("application/json")
    public Response getAll() {
        return Response.ok(service.getAll()).build();
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getById(@PathParam("id") Long id) {
        Company company = service.getById(id);

        if (company == null)
            return Response.status(Response.Status.NOT_FOUND).build();


        return Response.ok(company).build();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response create(Company company) {
        if (!service.create(company))
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();

        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Response update(Company company) {
        if(!service.update(company))
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
