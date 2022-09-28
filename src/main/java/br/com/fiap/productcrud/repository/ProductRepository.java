package br.com.fiap.productcrud.repository;

import br.com.fiap.productcrud.core.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
