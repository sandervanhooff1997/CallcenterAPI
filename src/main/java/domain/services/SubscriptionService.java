package domain.services;

import domain.models.Subscription;
import domain.repositories.SubscriptionRepository;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.List;

@Local
@Stateless
public class SubscriptionService {

    @EJB
    private SubscriptionRepository repository;

    public List<Subscription> getAll() {
        return repository.getAll();
    }

    public Subscription getById(Long id) {
        return repository.getById(id);
    }

    public boolean create(Subscription subscription) {
        if (subscription == null)
            return false;

        repository.create(subscription);
        return true;
    }

    public boolean update(Subscription subscription) {
        if (subscription == null)
            return false;

        repository.update(subscription);
        return true;
    }

    public boolean delete(Long id) {
        Subscription subscription = repository.getById(id);

        if (subscription == null)
            return false;

        repository.delete(subscription);
        return true;
    }
}
