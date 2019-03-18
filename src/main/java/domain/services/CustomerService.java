package domain.services;

import domain.models.Customer;
import domain.repositories.CustomerRepository;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.List;

@Local
@Stateless
public class CustomerService {

    @EJB
    private CustomerRepository repository;

    public List<Customer> getAll() {
        return repository.getAll();
    }

    public Customer getById(Long id) {
        return repository.getById(id);
    }

    public boolean create(Customer customer) {
        if (customer == null)
            return false;

        repository.create(customer);
        return true;
    }

    public boolean update(Customer customer) {
        if (customer == null)
            return false;

        repository.update(customer);
        return true;
    }

    public boolean delete(Long id) {
        Customer customer = repository.getById(id);

        if (customer == null)
            return false;

        repository.delete(customer);
        return true;
    }
}
