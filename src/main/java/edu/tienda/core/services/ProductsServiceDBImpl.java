package edu.tienda.core.services;

import edu.tienda.core.domain.Product;
import edu.tienda.core.persistance.entities.ProductEntity;
import edu.tienda.core.persistance.repositories.IProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        List<Product> products = productsRepository.findAll().
                stream().map(productEntity -> {
                    Product product = new Product();
                    product.setId(productEntity.getId());
                    product.setName(productEntity.getName());
                    product.setPrice(productEntity.getPrice());
                    product.setStock(productEntity.getStock());
                    return product;
                }).collect(Collectors.toList());

        return products;
    }
}
