package domain.controllers;

import domain.services.Auth.AuthorizationService;
import domain.services.Auth.JWTService;
import domain.utils.AuthenticationUtils;

import javax.ejb.EJB;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

@Path("authorization")
public class AuthorizationController {
    @EJB
    private AuthorizationService service;

    @EJB
    private JWTService jwtService;

    @POST
    @Path("/role/add/{roleId}/{employeeId}")
    public Response addRole(
            @Context HttpHeaders headers,
            @PathParam("roleId") Long roleId,
            @PathParam("employeeId") Long employeeId)
    {
        String token = AuthenticationUtils.getTokenFromHeader(headers);

        if (token == null)
            return Response.status(Response.Status.UNAUTHORIZED).build();

        if (!jwtService.isAdmin(token))
            return Response.status(Response.Status.UNAUTHORIZED).build();

        if (!service.assignRole(employeeId, roleId))
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok(Response.Status.OK).build();
    }
}
