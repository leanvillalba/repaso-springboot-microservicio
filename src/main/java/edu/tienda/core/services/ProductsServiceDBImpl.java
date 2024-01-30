package edu.tienda.core.services;

import edu.tienda.core.domain.Product;
import edu.tienda.core.persistance.entities.ProductEntity;
import edu.tienda.core.persistance.repositories.IProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("DB")
@ConditionalOnProperty(
        value = "products.strategy",
        havingValue = "IN_DB"

)
public class ProductsServiceDBImpl implements IProductService {

    @Autowired
    private IProductsRepository productsRepository; /*
    Cuando definimos una interfaz que extiende de “JpaRespository”, Spring
    crea una clase “concreta” en el “Start Up” de la aplicación de forma
    transparente e invisible a nosotros. Es esa clase concreta y “oculta”
    que Spring inyectará en este servicio.*/

    @Override
    public List<Product> getProducts() {
        List<ProductEntity> productEntities = productsRepository.findAll();

        return null;
    }
}
