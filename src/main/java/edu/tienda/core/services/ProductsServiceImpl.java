package edu.tienda.core.services;

import edu.tienda.core.domain.Product;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Lazy
@Service("MEMORY") // "(MEMORY") Gracias a esta nominación, desde el controlador podremos
                    // indicar de forma explícita cuál delos dos beans se debe inyectar.
/*
@SERVICE:

Con el objetivo principal de separar y desacoplar ya totalmente la clase de servicio con la
de controlador, vamos hacer uso de las inyecciones de dependencias de Spring.

Esta anotación hará que la clase quede registrada por Spring en su contenedor de
dependencias. De esta manera será Spring el encargado de producir instancias de esta clase
automáticamente. Este tipo patrón se llama “inversión del control" y también se lo conoce como
patrón “Hollywood”
*/
@ConditionalOnProperty(
        value = "products.strategy",
        havingValue = "IN_MEMORY"
)

public class ProductsServiceImpl implements IProductService {

    private List<Product> products = new ArrayList<>(Arrays.asList(
            new Product(1, "Smart TV", 9000.0, 3),
            new Product(2, "Pc Notebook", 15000.0, 10),
            new Product(3, "Tablet", 8000.0, 5)
    ));

    public ProductsServiceImpl() {
        System.out.println("Se está construyendo un objeto de la clase ProductsServiceImpl");
    }
    public List<Product> getProducts() {
        return products;
    }

    @Override
    public List<Product> getProductsByPriceLessThan(Double price) {
        return null;
    }

    @Override
    public List<Product> getProductsByNameLike(String name) {
        return null;
    }

    @Override
    public List<Product> getProductsByPriceGreaterThanAndStockLessThan(Double price, Integer stock) {
        return null;
    }

    @Override
    public void saveProduct(Product product) {

    }

    @Override
    public Product updateProduct(Product product) {
        return product;

    }

    @Override
    public void deleteProduct(Integer id) {

    }

}
