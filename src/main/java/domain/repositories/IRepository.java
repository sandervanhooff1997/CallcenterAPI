package domain.repositories;

import org.hibernate.HibernateException;

import java.util.List;

public interface IRepository<T> {
    T getById(Long id) throws HibernateException;

    List<T> getAll() throws HibernateException;

    void save(T t) throws HibernateException;

    void update(T t) throws HibernateException;

    void delete(T t) throws HibernateException;
}
