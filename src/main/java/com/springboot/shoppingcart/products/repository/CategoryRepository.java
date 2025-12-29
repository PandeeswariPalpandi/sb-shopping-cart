package com.springboot.shoppingcart.products.repository;

import com.springboot.shoppingcart.products.entity.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Byte> {
}