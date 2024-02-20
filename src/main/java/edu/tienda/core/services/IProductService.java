package edu.tienda.core.services;

import edu.tienda.core.domain.Product;

import java.util.List;

public interface IProductService {
    public List<Product> getProducts();

    public List<Product> getProductsByPriceLessThan(Double price);

    public List<Product> getProductsByNameLike(String name);

    public List<Product> getProductsByPriceGreaterThanAndStockLessThan(Double price, Integer stock);

    public List<Product> getProductsByNameAndPriceAndStock(String name, Double price, Integer stock);

    public void saveProduct(Product product);

    public Product updateProduct(Product product);

    public void deleteProduct(Integer id);
}
