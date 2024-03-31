package com.example.Warehouse.service;

import com.example.Warehouse.exception.MaterialAlreadyExistException;
import com.example.Warehouse.exception.MaterialUsedException;
import com.example.Warehouse.model.Material;
import com.example.Warehouse.repository.BedLinenRepository;
import com.example.Warehouse.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MaterialService {
    private final MaterialRepository materialRepository;

    private final BedLinenRepository bedLinenRepository;

    private final LogService logService;

    @Autowired
    public MaterialService(MaterialRepository materialRepository, BedLinenRepository bedLinenRepository, LogService logService) {
        this.materialRepository = materialRepository;
        this.bedLinenRepository = bedLinenRepository;
        this.logService = logService;
    }

    public List<Material> getMaterials(){ return materialRepository.findAll(); }

    public Material getMaterial(int id) { return materialRepository.findById(id).orElse(null); }

    @Transactional
    public void addOrUpdateMaterial(Material material) throws MaterialAlreadyExistException {
        if(materialRepository.findByTitle(material.getTitle()) != null) {
            throw new MaterialAlreadyExistException();
        }
        if(material.getId() == 0) {
            logService.addLog("Добавлен материал для постельного белья");
        }
        else{
            logService.addLog("Изменен материал для постельного белья");
        }
        materialRepository.save(material);
    }

    @Transactional
    public void deleteMaterial(int id) throws MaterialUsedException{
        if(bedLinenRepository.findByIdMaterial(id) != null) {
            throw new MaterialUsedException();
        }
        materialRepository.deleteById(id);
        logService.addLog("Удален материал для постельного белья");
    }

    public Integer getCountMaterials() { return materialRepository.getCountMaterials(); }
}