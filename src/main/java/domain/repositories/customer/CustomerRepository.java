package domain.repositories.customer;

import domain.models.Customer;
import domain.repositories.CRUDRepository;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

@Local
@Stateless
@Default
public class CustomerRepository extends CRUDRepository<Customer> implements ICustomerRepository {

}
