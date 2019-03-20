package domain.services.customer;

import domain.models.Customer;
import domain.repositories.customer.ICustomerRepository;
import domain.services.CRUDService;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

@Local
@Stateless
public class CustomerService extends CRUDService<Customer> implements ICustomerService {

    @EJB
    private ICustomerRepository repository;
}
