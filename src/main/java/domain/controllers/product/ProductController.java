package domain.controllers.product;

import domain.controllers.CRUDController;
import domain.models.Product;
import domain.services.product.IProductService;

import javax.ejb.EJB;
import javax.ws.rs.Path;

@Path("product")
public class ProductController extends CRUDController<Product> implements IProductController {

    @EJB
    private IProductService service;
}
