package com.webapp.techshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webapp.techshop.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
	List <Product> findByTitle(String title);
}