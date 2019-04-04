package domain.repositories;

import domain.models.Company;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Local
@Stateless
public class CompanyRepository {
    @PersistenceContext(unitName = "callcenterPU")
    private EntityManager em;

    public List<Company> getAll() {
        return em.createNamedQuery("Company.getAllRoles", Company.class).getResultList();
    }

    public Company getById(Long id) {
        return em.createNamedQuery("Company.getRoleById", Company.class).setParameter("id", id).getSingleResult();
    }

    public void create(Company company) {
        em.persist(company);
    }

    public void update(Company company) {
        em.merge(company);
    }

    public void delete(Company company) {
        em.remove(company);
    }
}
