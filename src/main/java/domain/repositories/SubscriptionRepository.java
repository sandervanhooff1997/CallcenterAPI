package domain.repositories;

import domain.models.Subscription;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Local
@Stateless
public class SubscriptionRepository {

    @PersistenceContext(unitName = "callcenterPU")
    private EntityManager em;

    public List<Subscription> getAll() {
        return em.createNamedQuery("Subscription.getAll", Subscription.class).getResultList();
    }

    public Subscription getById(Long id) {
        return em.createNamedQuery("Subscription.getById", Subscription.class).setParameter("id", id).getSingleResult();
    }

    public void create(Subscription subscription) {
        em.persist(subscription);
    }

    public void update(Subscription subscription) {
        em.merge(subscription);
    }

    public void delete(Subscription subscription) {
        em.remove(subscription);
    }
}
