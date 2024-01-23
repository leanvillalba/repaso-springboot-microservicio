package edu.tienda.core.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.tienda.core.domain.Product;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
@Service("JSON")
@ConditionalOnProperty(
        value = "products.strategy",
        havingValue = "IN_JSON"
)
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
