package edu.tienda.core.persistance.repositories;

import edu.tienda.core.persistance.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductsRepository extends JpaRepository<ProductEntity, Integer> {

    List<ProductEntity> findByPriceLessThan(Double price);

    List<ProductEntity> findByNameLike(String name);

    List<ProductEntity> findByPriceGreaterThanAndStockLessThan(Double price, Integer stock);

    @Query(value = "SELECT * FROM products where name =?1 and price =?2 and stock =?3", nativeQuery = true)
    List<ProductEntity> findByNameAndPriceAndstock(String name, Double price, Integer stock);

}
