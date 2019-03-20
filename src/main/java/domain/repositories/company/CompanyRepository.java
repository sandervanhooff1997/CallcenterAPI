package domain.repositories.company;

import domain.models.Company;
import domain.repositories.CRUDRepository;

import javax.ejb.Local;
import javax.ejb.Stateless;

@Local
@Stateless
public class CompanyRepository extends CRUDRepository<Company> implements ICompanyRepository {

}
