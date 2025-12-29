package com.springboot.shoppingcart.users.repository;

import com.springboot.shoppingcart.users.entity.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
}