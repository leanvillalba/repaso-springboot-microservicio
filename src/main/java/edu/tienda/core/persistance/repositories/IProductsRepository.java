package edu.tienda.core.persistance.repositories;

import edu.tienda.core.persistance.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductsRepository extends JpaRepository<ProductEntity, Integer> {

    List<ProductEntity> findByPriceLessThan(Double price);

    List<ProductEntity> findByNameLike(String name);

    List<ProductEntity> findByPriceGreaterThanAndStockLessThan(Double price, Integer stock);

}
