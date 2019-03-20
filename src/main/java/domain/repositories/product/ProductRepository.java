package domain.repositories.product;

import domain.models.Product;
import domain.repositories.CRUDRepository;

import javax.ejb.Local;
import javax.ejb.Stateless;

@Local
@Stateless
public class ProductRepository extends CRUDRepository<Product> implements IProductRepository {

}
