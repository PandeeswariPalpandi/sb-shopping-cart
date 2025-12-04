package com.springboot.shoppingcart.repositories;

import com.springboot.shoppingcart.entities.Profile;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<Profile, Long> {
}
