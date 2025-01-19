package org.stronglover.demospringbootandjasperrepport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.stronglover.demospringbootandjasperrepport.entity.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
}
