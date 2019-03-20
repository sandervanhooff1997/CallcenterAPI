package domain.controllers.employee;

import domain.controllers.IController;
import domain.models.Employee;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

public interface IEmployeeController extends IController<Employee>{
    @GET
    @Path("/email/{email}")
    @Produces("application/json")
    @Consumes("application/json")
    Response getByEmail(@PathParam("email") String email);
}
