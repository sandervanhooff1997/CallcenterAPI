package domain.repositories.employee;

import domain.models.Employee;
import domain.repositories.IRepository;
import org.hibernate.HibernateException;

public interface IEmployeeRepository extends IRepository<Employee> {
    Employee getByEmail(String email) throws HibernateException;
    Employee getByEmailAndPassword(String email, String password) throws HibernateException;
}
