package com.example.Warehouse.repository;

import com.example.Warehouse.model.Pillow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PillowRepository extends JpaRepository<Pillow, Integer> {
    List<Pillow> findByTitle(String title);

    @Query(
            nativeQuery = true,
            value = "SELECT count_pillows()")
    Integer getCountPillows();
}