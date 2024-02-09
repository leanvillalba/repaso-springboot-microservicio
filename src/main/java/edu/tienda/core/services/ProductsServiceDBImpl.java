package edu.tienda.core.services;

import edu.tienda.core.domain.Product;
import edu.tienda.core.exceptions.BadRequestException;
import edu.tienda.core.persistance.entities.ProductEntity;
import edu.tienda.core.persistance.repositories.IProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
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

    @Override
    public void saveProduct(Product product) {
        // Mapeo de Product a ProductEntity
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(product.getName());
        productEntity.setPrice(product.getPrice());
        productEntity.setStock(product.getStock());
        /* En este caso hemos implementado el procedimiento inverso
        al método que recupera los productos.*/

        // Persistencia
        productsRepository.save(productEntity);

    }

    @Override
    public Product updateProduct(Product product) {
        Optional<ProductEntity> existingProductEntity = productsRepository.findById(product.getId());

        if (existingProductEntity.isPresent()) {
            ProductEntity productToUpdate = existingProductEntity.get();

            // Actualizo los campos de la entidad con los nuevos datos
            productToUpdate.setName(product.getName());
            productToUpdate.setPrice(product.getPrice());
            productToUpdate.setStock(product.getStock());

            // Guardo la entidad actualizada
            productsRepository.save(productToUpdate);

            return product;
        } else {
            // Manejo el caso en el que no exista un producto con el id
            throw new BadRequestException("No se encontró el producto con el ID " + product.getId());

        }

    }

    @Override
    public void deleteProduct(Integer id) {
        productsRepository.deleteById(id);
    }
}
