package domain.repositories;

import domain.models.Product;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Local
@Stateless
public class ProductRepository {

    @PersistenceContext(unitName = "callcenterPU")
    private EntityManager em;

    public List<Product> getAll() {
        return em.createNamedQuery("Product.getAll", Product.class).getResultList();
    }

    public Product getById(Long id) {
        return em.createNamedQuery("Product.getById", Product.class).setParameter("id", id).getSingleResult();
    }

    public void create(Product product) {
        em.persist(product);
    }

    public void update(Product product) {
        em.merge(product);
    }

    public void delete(Product product) {
        em.remove(product);
    }
}
