package domain.services;

import java.util.List;

public interface IService<T> {
    T getById(Long id);

    List<T> getAll();

    void save(T t);

    void update(T t);

    void delete(Long id);
}
