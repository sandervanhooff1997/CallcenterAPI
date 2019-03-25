package domain.services;

import domain.interceptors.CallcenterInterceptor;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.logging.Logger;

@Local
@Stateless
@Interceptors(CallcenterInterceptor.class)
public abstract class BaseService {
    protected Logger logger = Logger.getLogger(this.getClass().getName());

}
