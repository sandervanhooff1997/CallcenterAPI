package domain.repositories.company;

import domain.models.Company;
import domain.repositories.CRUDRepository;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

@Local
@Stateless
@Default
public class CompanyRepository extends CRUDRepository<Company> implements ICompanyRepository {

}
