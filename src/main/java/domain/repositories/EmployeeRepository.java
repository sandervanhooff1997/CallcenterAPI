package domain.repositories;

import domain.models.Employee;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Local
@Stateless
public class EmployeeRepository {

    @PersistenceContext(unitName = "callcenterPU")
    private EntityManager em;

    public List<Employee> getAll() {
        return em.createNamedQuery("Employee.getAll", Employee.class).getResultList();
    }

    public Employee getById(Long id) {
        return em.createNamedQuery("Employee.getById", Employee.class).setParameter("id", id).getSingleResult();
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
