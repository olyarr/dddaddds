package com.example.Warehouse.repository;

import com.example.Warehouse.model.Blanket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlanketRepository extends JpaRepository<Blanket, Integer> {
    List<Blanket> findByTitle(String title);

    @Query(
            nativeQuery = true,
            value = "SELECT count_blankets()")
    Integer getCountBlankets();
}