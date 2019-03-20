package domain.services.employee;

import domain.models.Employee;
import domain.services.IService;


public interface IEmployeeService extends IService<Employee> {
    Employee getByEmail(String email);
}
