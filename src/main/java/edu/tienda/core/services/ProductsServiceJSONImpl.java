package edu.tienda.core.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.tienda.core.domain.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
@Primary /* Distingue a un bean de otro indicando que el que está marcado
        con esta anotación debe tener prioridad para la inyección por sobre otros
        servicios “hermanos”. Es decir, servicios que implementan una misma interfaz. */
@Service
public class ProductsServiceJSONImpl implements IProductService {

    @Override
    public List<Product> getProducts() {
    /*
    * Carga del archivo JSON de productos desde el classpath del proyecto
    * y se deserializa a una lista de productos.*/

        List<Product> products;
        try {

            products = new ObjectMapper()
                    .readValue(this.getClass().getResourceAsStream("/products.json"),
                            new TypeReference<List<Product>>() {});

            return products;

        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
