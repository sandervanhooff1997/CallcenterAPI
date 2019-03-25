package domain.repositories;

import domain.models.Call;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Local
@Stateless
public class CallRepository {
    @PersistenceContext(unitName = "callcenterPU")
    private EntityManager em;

    public List<Call> getAll() {
        try {
            return em.createQuery("SELECT c FROM Call c", Call.class).getResultList();
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public Call getById(Long id) {
        return em.createNamedQuery("Call.getById", Call.class).setParameter("id", id).getSingleResult();
    }

    public void create(Call call) {
        em.persist(call);
    }

    public void update(Call call) {
        em.merge(call);
    }

    public void delete(Call call) {
        em.remove(call);
    }
}
