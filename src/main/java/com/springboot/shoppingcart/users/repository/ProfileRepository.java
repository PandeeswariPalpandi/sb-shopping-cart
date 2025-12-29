package com.springboot.shoppingcart.users.repository;

import com.springboot.shoppingcart.users.entity.Profile;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<Profile, Long> {
}