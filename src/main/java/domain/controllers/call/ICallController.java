package domain.controllers.call;

import domain.controllers.IController;
import domain.models.Call;

import javax.ejb.Local;

@Local
public interface ICallController extends IController<Call> {
}
