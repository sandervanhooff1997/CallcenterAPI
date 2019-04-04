package domain.repositories;

import domain.models.Auth.Role;
import domain.models.Call;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Local
@Stateless
public class RoleRepository {
    @PersistenceContext(unitName = "callcenterPU")
    private EntityManager em;

    public Role getById(Long id) {
        return em.createNamedQuery("Role.getRoleById", Role.class).setParameter("id", id).getSingleResult();
    }

    public void delete(Role role) {
        em.remove(role);
    }
}
