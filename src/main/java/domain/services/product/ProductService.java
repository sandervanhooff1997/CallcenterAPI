package domain.services.product;

import domain.models.Product;
import domain.repositories.product.IProductRepository;
import domain.services.CRUDService;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

@Local
@Stateless
public class ProductService extends CRUDService<Product> implements IProductService {

    @EJB
    private IProductRepository repository;
}
