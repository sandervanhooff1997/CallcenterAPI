package domain.repositories.product;

import domain.models.Product;
import domain.repositories.CRUDRepository;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

@Local
@Stateless
@Default
public class ProductRepository extends CRUDRepository<Product> implements IProductRepository {

}
