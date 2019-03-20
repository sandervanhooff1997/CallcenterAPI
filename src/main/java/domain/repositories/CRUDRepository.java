package domain.repositories;

import domain.interceptors.CallcenterInterceptor;
import org.hibernate.HibernateException;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.logging.Logger;

@Local
@Stateless
@Interceptors(CallcenterInterceptor.class)
public abstract class CRUDRepository<T> implements IRepository<T> {
    @PersistenceContext(unitName = "callcenterPU")
    protected EntityManager em;

    protected Logger logger = Logger.getLogger(this.getClass().getName());

    private Class<T> type;

    public CRUDRepository() {
    }

    public CRUDRepository(Class<T> type){
        this.type = type;
    }

    @Override
    public T getById(Long id) {
        try {
            return em.find(type, id);
        } catch (HibernateException e) {
            logger.warning(e.getMessage());
            return null;
        }
    }

    @Override
    public List<T> getAll() {
        try {
            return em.createNamedQuery(type.getSimpleName() +".getAll", type).getResultList();
        } catch (HibernateException e) {
            logger.warning(e.getMessage());
            return null;
        }
    }

    @Override
    public void save(T t) {
        try {
            em.persist(t);
        } catch (HibernateException e) {
            logger.warning(e.getMessage());
        }
    }

    @Override
    public void update(T t) {
        try {
            em.merge(t);
        } catch (HibernateException e) {
            logger.warning(e.getMessage());
        }
    }

    @Override
    public void delete(T t) {
        try {
            em.remove(t);
        } catch (HibernateException e) {
            logger.warning(e.getMessage());
        }
    }
}
