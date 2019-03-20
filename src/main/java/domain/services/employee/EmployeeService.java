package domain.services.employee;

import domain.models.Employee;
import domain.repositories.employee.IEmployeeRepository;
import domain.services.CRUDService;
import org.hibernate.HibernateException;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

@Local
@Stateless
public class EmployeeService extends CRUDService<Employee> implements IEmployeeService  {

    @EJB
    private IEmployeeRepository repository;

    @Override
    public Employee getByEmail(String email) {
        try {
            return repository.getByEmail(email);
        } catch (HibernateException e) {
            logger.warning(e.getMessage());
            return null;
        }
    }
}
