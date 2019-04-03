package domain.repositories;

import domain.models.Auth.Role;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Local
@Stateless
public class AuthorisationRepository {
    @PersistenceContext(unitName = "callcenterPU")
    private EntityManager em;

    public List<Role> getAll() {
        try {
            return em.createQuery("SELECT g FROM Role g", Role.class).getResultList();
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public Role getById(Long id) {
        return em.createQuery("SELECT e FROM Role e where e.id = :id", Role.class).setParameter("id", id).getSingleResult();
    }

    public void create(Role role) {
        em.persist(role);
    }

    public void update(Role role) {
        em.merge(role);
    }

    public void delete(Role role) {
        em.remove(role);
    }
}
