package domain.services;

import domain.interceptors.CallcenterInterceptor;
import domain.repositories.IRepository;
import org.hibernate.HibernateException;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.MappedSuperclass;
import java.util.List;
import java.util.logging.Logger;

@Local
@Stateless
@MappedSuperclass
@Interceptors(CallcenterInterceptor.class)
public abstract class CRUDService<T> implements IService<T> {
    protected Logger logger = Logger.getLogger(this.getClass().getName());

    protected IRepository<T> repository;

    @Override
    public T getById(Long id) {
        try {
            return repository.getById(id);
        } catch (HibernateException e) {
            logger.warning(e.getMessage());
            return null;
        }
    }

    @Override
    public List<T> getAll() {
        try {
            return repository.getAll();
        } catch (HibernateException e) {
            logger.warning(e.getMessage());
            return null;
        }
    }

    @Override
    public void save(T t) {
        try {
            repository.save(t);
        } catch (HibernateException e) {
            logger.warning(e.getMessage());
        }
    }

    @Override
    public void update(T t) {
        try {
            repository.update(t);
        } catch (HibernateException e) {
            logger.warning(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try {
            T entity = getById(id);
            repository.delete(entity);
        } catch (HibernateException e) {
            logger.warning(e.getMessage());
        }
    }
}
