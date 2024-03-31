package com.example.Warehouse.repository;

import com.example.Warehouse.model.MaterialCover;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialCoverRepository extends CrudRepository<MaterialCover, Integer> {
    MaterialCover findByTitle(String title);

    @Query(
            nativeQuery = true,
            value = "SELECT count_materials_cover()")
    Integer getCountMaterialsCover();
}
