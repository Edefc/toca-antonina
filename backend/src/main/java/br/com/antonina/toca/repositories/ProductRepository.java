package br.com.antonina.toca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.antonina.toca.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	
}
