package domain.repositories.subscription;

import domain.models.Subscription;
import domain.repositories.CRUDRepository;

import javax.ejb.Local;
import javax.ejb.Stateless;

@Local
@Stateless
public class SubscriptionRepository extends CRUDRepository<Subscription> implements ISubscriptionRepository {

}
