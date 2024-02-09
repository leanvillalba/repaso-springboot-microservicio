package edu.tienda.core.services;

import edu.tienda.core.domain.Product;

import java.util.List;

public interface IProductService {
    public List<Product> getProducts();

    public void saveProduct(Product product);

    public Product updateProduct(Product product);

    public void deleteProduct(Integer id);
}
