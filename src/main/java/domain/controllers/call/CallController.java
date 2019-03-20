package domain.controllers.call;

import domain.controllers.CRUDController;
import domain.models.Call;
import domain.services.call.ICallService;

import javax.ejb.EJB;
import javax.ws.rs.*;

@Path("call")
public class CallController extends CRUDController<Call> implements ICallController{

    @EJB
    private ICallService service;

}
