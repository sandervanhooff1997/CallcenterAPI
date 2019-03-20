package domain.services.company;

import domain.models.Company;
import domain.repositories.company.ICompanyRepository;
import domain.services.CRUDService;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

@Local
@Stateless
public class CompanyService extends CRUDService<Company> implements ICompanyService {

    @EJB
    private ICompanyRepository repository;
}
