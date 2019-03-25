package domain.controllers.employee;

import domain.controllers.CRUDController;
import domain.models.Employee;
import domain.services.employee.IEmployeeService;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("employee")
public class EmployeeController extends CRUDController<Employee> implements IEmployeeController {

    @EJB
    private IEmployeeService service;

    @Override
    public Response getByEmail(String email) {
        try {
            Employee e = service.getByEmail(email);

            if (e == null)
                return Response.status(Response.Status.NOT_FOUND).build();

            return Response.ok(e).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
