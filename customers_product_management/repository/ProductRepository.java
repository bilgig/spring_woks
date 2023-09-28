package com.salessite.management.repository;

import com.salessite.management.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
        Optional<Product> findByProductName(String productName);

        @Query("SELECT s FROM Product s WHERE s.price >= :maxPrice")
        List<Product> findProductsByPriceJPQL(@Param("maxPrice") double maxPrice);


}
