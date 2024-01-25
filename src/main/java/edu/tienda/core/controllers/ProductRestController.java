package edu.tienda.core.controllers;

import edu.tienda.core.configurations.ConfigurationParameters;
import edu.tienda.core.domain.Product;
import edu.tienda.core.services.IProductService;
import edu.tienda.core.services.ProductsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductRestController {

    @Autowired
    /* La anotación @Autowired indica a Spring que tiene que buscar en su contenedor de
    inyecciones una clase que implemente a la interfaz que decora. Luego Spring, creará
    una instancia de “ProductsServiceImpl” y la guardará en la referencia “IProductService”
    de manera transparente tanto al programador como a la clase controladora.*/
    //@Qualifier("MEMORY") Quitamos esta anotación para que no se interponga con @ConditionalOnProperty
    @Lazy
    private IProductService productsService;

    @GetMapping
    public ResponseEntity<?> getProducts() {

        System.out.println("Params: " + configurationParameters.toString());

        // Se recuperan todos los productos del servicio.
        List<Product> products = productsService.getProducts();

        // Retornamos los productos del servicio en el boy de la respuesta.
        return ResponseEntity.ok(products);
    }

    @Autowired
    private ConfigurationParameters configurationParameters;

    @GetMapping("/fake-products")
    public ResponseEntity<?> fakeProductsAPI(){

        List<Product> products = new ArrayList<>(Arrays.asList(
                new Product(1, "Camiseta Manchester City", 1200.0, 4),
                new Product(2, "Camiseta Boca Jrs", 1000.0, 5),
                new Product(3, "Camiseta River Plate", 900.0, 6)
                )
        );

        // Retornamos los productos del servicio en el body de la respuesta.
        return ResponseEntity.ok(products);
    }
}
