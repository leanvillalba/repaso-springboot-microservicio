package edu.tienda.core.services;

import edu.tienda.core.domain.Product;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Service("API")
@ConditionalOnProperty(
        value = "products.strategy",
        havingValue = "IN_OTHER_API")
public class ProductServiceImpExternalApi implements IProductService{
    @Override
    public List<Product> getProducts() {

        RestTemplate restTemplate = new RestTemplate();

        /* Ejecutamos el método “exchange” que sirve para consumir el contenido de una
        URL (Aclaramos que puede ser cualquier tipo de contenido como HTML, XML,
        Texto Plano,etc), que en este caso es de tipo JSON. La URL que pasamos es la
        de nuestra “API Fake”.*/

        ResponseEntity<List<Product>> response = restTemplate.
                exchange("http://localhost:8080/tienda/api/v1/products/fake-products",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Product>>() {
                        });
        List<Product> products = response.getBody();

        return products;
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
