package domain.services;

import domain.models.Product;
import domain.repositories.ProductRepository;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.List;

@Local
@Stateless
public class ProductService {

    @EJB
    private ProductRepository repository;

    public List<Product> getAll() {
        return repository.getAll();
    }

    public Product getById(Long id) {
        return repository.getById(id);
    }

    public boolean create(Product product) {
        if (product == null)
            return false;

        repository.create(product);
        return true;
    }

    public boolean update(Product product) {
        if (product == null)
            return false;

        repository.update(product);
        return true;
    }

    public boolean delete(Long id) {
        Product product = repository.getById(id);

        if (product == null)
            return false;

        repository.delete(product);
        return true;
    }
}
