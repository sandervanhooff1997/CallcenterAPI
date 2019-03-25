package domain.repositories;

import domain.models.Customer;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Local
@Stateless
public class CustomerRepository {

    @PersistenceContext(unitName = "callcenterPU")
    private EntityManager em;

    public List<Customer> getAll() {
        return em.createNamedQuery("Customer.getAll", Customer.class).getResultList();
    }

    public Customer getById(Long id) {
        return em.createNamedQuery("Customer.getById", Customer.class).setParameter("id", id).getSingleResult();
    }

    public void create(Customer customer) {
        em.persist(customer);
    }

    public void update(Customer customer) {
        em.merge(customer);
    }

    public void delete(Customer customer) {
        em.remove(customer);
    }
}
