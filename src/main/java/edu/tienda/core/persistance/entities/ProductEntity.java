package edu.tienda.core.persistance.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "products")
@Data
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE) // Es la más usual en postgres
    private Integer id;
    private String name;
    private Double price;
    private Integer stock;
}
