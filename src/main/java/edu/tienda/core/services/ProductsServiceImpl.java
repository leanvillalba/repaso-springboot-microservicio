package edu.tienda.core.services;

import edu.tienda.core.domain.Product;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    public List<Product> getProducts() {
        return products;
    }
}
