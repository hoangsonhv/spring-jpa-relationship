package com.example.demospring.sell.repository;

import com.example.demospring.sell.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(value = "SELECT * FROM products p WHERE p.name LIKE %:name% and p.name LIKE %:description%",
            nativeQuery = true)
    List<Product> search(@Param("name") String name, @Param("description") String description);

    // Automatic Custom Queries
    List<Product> findAllByNameContainsAndPriceLessThanEqual(String name, double price);

    @Query(value = "select * from products p WHERE p.name LIKE %:name% and p.price <= :price", nativeQuery = true)
    List<Object[]> search(@Param("name") String name, @Param("price") double price);

}
