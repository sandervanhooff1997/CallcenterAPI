package domain.services;

import domain.models.Employee;
import domain.repositories.EmployeeRepository;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.List;

@Local
@Stateless
public class EmployeeService {

    @EJB
    private EmployeeRepository repository;

    public List<Employee> getAll() {
        return repository.getAll();
    }

    public Employee getById(Long id) {
        return repository.getById(id);
    }

    public boolean create(Employee employee) {
        if (employee == null)
            return false;

        repository.create(employee);
        return true;
    }

    public boolean update(Employee employee) {
        if (employee == null)
            return false;

        repository.update(employee);
        return true;
    }

    public boolean delete(Long id) {
        Employee employee = repository.getById(id);

        if (employee == null)
            return false;

        repository.delete(employee);
        return true;
    }
}
