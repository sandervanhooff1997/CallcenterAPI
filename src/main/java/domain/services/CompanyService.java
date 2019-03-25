package domain.services;

import domain.models.Company;
import domain.repositories.CompanyRepository;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.List;

@Local
@Stateless
public class CompanyService {

    @EJB
    private CompanyRepository repository;

    public List<Company> getAll() {
        return repository.getAll();
    }

    public Company getById(Long id) {
        return repository.getById(id);
    }

    public boolean create(Company company) {
        if (company == null)
            return false;

        repository.create(company);
        return true;
    }

    public boolean update(Company company) {
        if (company == null)
            return false;

        repository.update(company);
        return true;
    }

    public boolean delete(Long id) {
        Company company = repository.getById(id);

        if (company == null)
            return false;

        repository.delete(company);
        return true;
    }
}
