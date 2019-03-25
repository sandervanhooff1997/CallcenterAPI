package domain.repositories;

import domain.models.Employee;

import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.List;

@Local
@Stateless
public class EmployeeRepository extends BaseRepository {
    public List<Employee> getAll() {
        return em.createNamedQuery("Employee.getAll", Employee.class).getResultList();
    }

    public Employee getById(Long id) {
        return em.createNamedQuery("Employee.getById", Employee.class).setParameter("id", id).getSingleResult();
    }

    public Employee getByEmail(String email) {
        try {
            return em.createNamedQuery("Employee.getByEmail", Employee.class).setParameter("email", email).getSingleResult();
        } catch (Exception e) {
            logger.warning(e.getMessage());
            return null;
        }
    }

    public Employee getByEmailAndPassword(String email, String password) {
        try {
            return em.createNamedQuery("Employee.getByEmailAndPassword", Employee.class)
                    .setParameter("email", email)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (Exception e) {
            logger.warning(e.getMessage());
            return null;
        }
    }

    public void create(Employee employee) {
        em.persist(employee);
    }

    public void update(Employee employee) {
        em.merge(employee);
    }

    public void delete(Employee employee) {
        em.remove(employee);
    }
}
