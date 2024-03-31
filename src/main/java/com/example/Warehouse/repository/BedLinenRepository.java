package com.example.Warehouse.repository;

import com.example.Warehouse.model.BedLinen;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BedLinenRepository extends CrudRepository<BedLinen, Integer> {
    List<BedLinen> findByTitle(String title);

    @Query(
            nativeQuery = true,
            value = "select * from bedlinen where material_id = ?1")
    List<BedLinen> findByIdMaterial(int id);

    @Query(
            nativeQuery = true,
            value = "SELECT count_bedlinens()")
    Integer getCountBedLinens();
}