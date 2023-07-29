package com.mohsenfn.authservice.Repository;

import com.mohsenfn.authservice.Entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Long> {
    Optional<User> findByUsername(String name);
}
