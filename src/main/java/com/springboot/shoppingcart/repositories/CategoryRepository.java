package com.springboot.shoppingcart.repositories;

import com.springboot.shoppingcart.entities.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Byte> {
}
