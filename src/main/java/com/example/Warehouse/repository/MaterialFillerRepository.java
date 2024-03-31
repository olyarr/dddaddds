package com.example.Warehouse.repository;

import com.example.Warehouse.model.MaterialFiller;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialFillerRepository extends CrudRepository<MaterialFiller, Integer> {
    MaterialFiller findByTitle(String title);

    @Query(
            nativeQuery = true,
            value = "SELECT count_materials_filler()")
    Integer getCountMaterialsFiller();
}
