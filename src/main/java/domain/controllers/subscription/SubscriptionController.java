package domain.controllers.subscription;

import domain.controllers.CRUDController;
import domain.models.Subscription;
import domain.services.subscription.ISubscriptionService;

import javax.ejb.EJB;
import javax.ws.rs.Path;

@Path("subscription")
public class SubscriptionController extends CRUDController<Subscription> implements ISubscriptionController {

    @EJB
    private ISubscriptionService service;
}
