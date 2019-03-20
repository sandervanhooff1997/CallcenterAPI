package domain.controllers.customer;

import domain.controllers.CRUDController;
import domain.models.Customer;

import javax.ejb.EJB;
import javax.ws.rs.Path;

@Path("customer")
public class CustomerController extends CRUDController<Customer> implements ICustomerController {

    @EJB
    private ICustomerController service;
}
