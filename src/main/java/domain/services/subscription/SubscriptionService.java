package domain.services.subscription;

import domain.models.Subscription;
import domain.repositories.subscription.ISubscriptionRepository;
import domain.services.CRUDService;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

@Local
@Stateless
@Default
public class SubscriptionService extends CRUDService<Subscription> implements ISubscriptionService {

    @EJB
    private ISubscriptionRepository repository;
}
