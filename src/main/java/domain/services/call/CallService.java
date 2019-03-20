package domain.services.call;

import domain.models.Call;
import domain.repositories.call.ICallRepository;
import domain.services.CRUDService;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

@Local
@Stateless
public class CallService extends CRUDService<Call> implements ICallService {

    @EJB
    private ICallRepository repository;
}
