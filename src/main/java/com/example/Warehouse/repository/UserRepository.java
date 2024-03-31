package com.example.Warehouse.repository;

import com.example.Warehouse.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByUsername(String username);

    @Query(
            nativeQuery = true,
            value = "SELECT count_users()")
    Integer getCountUsers();
}
