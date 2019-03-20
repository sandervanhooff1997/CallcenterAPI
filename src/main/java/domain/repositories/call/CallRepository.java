package domain.repositories.call;

import domain.models.Call;
import domain.repositories.CRUDRepository;

import javax.ejb.Local;
import javax.ejb.Stateless;

@Local
@Stateless
public class CallRepository extends CRUDRepository<Call> implements ICallRepository{

}
