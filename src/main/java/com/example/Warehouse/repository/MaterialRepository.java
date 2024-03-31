package com.example.Warehouse.repository;

import com.example.Warehouse.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Integer> {
    Material findByTitle(String title);

    @Query(
            nativeQuery = true,
            value = "SELECT count_materials()")
    Integer getCountMaterials();
}

