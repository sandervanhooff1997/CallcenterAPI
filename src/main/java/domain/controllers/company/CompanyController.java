package domain.controllers.company;

import domain.controllers.CRUDController;
import domain.models.Call;
import domain.models.Company;
import domain.services.call.ICallService;

import javax.ejb.EJB;
import javax.ws.rs.Path;

@Path("company")
public class CompanyController extends CRUDController<Company> implements ICompanyController {

    @EJB
    private ICompanyController service;
}
