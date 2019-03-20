package domain.repositories.employee;

import domain.models.Employee;
import domain.repositories.CRUDRepository;
import org.hibernate.HibernateException;

import javax.ejb.Local;
import javax.ejb.Stateless;

@Local
@Stateless
public class EmployeeRepository extends CRUDRepository<Employee> implements IEmployeeRepository {
    @Override
    public Employee getByEmail(String email) throws HibernateException {
        try {
            return em.createNamedQuery("Employee.getByEmail", Employee.class).setParameter("email", email).getSingleResult();
        } catch (HibernateException e) {
            logger.warning(e.getMessage());
            return null;
        }
    }

    @Override
    public Employee getByEmailAndPassword(String email, String password) throws HibernateException {
        try {
            return em
                    .createNamedQuery("Employee.getByEmailAndPassword", Employee.class)
                    .setParameter("email", email)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (HibernateException e) {
            logger.warning(e.getMessage());
            return null;
        }
    }
}
