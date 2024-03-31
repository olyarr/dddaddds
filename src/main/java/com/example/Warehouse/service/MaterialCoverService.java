package com.example.Warehouse.service;

import com.example.Warehouse.exception.MaterialCoverAlreadyExistException;
import com.example.Warehouse.model.MaterialCover;
import com.example.Warehouse.repository.MaterialCoverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MaterialCoverService {
    private final MaterialCoverRepository materialCoverRepository;

    private final LogService logService;

    @Autowired
    public MaterialCoverService(MaterialCoverRepository materialCoverRepository, LogService logService) {
        this.materialCoverRepository = materialCoverRepository;
        this.logService = logService;
    }

    public List<MaterialCover> getMaterialsCover() {
        return (List<MaterialCover>) materialCoverRepository.findAll();
    }

    public MaterialCover getMaterialCover(int id){
        return materialCoverRepository.findById(id).orElse(null);
    }

    @Transactional
    public void addOrUpdateMaterialCover(MaterialCover materialCover) throws MaterialCoverAlreadyExistException {
        if (materialCoverRepository.findByTitle(materialCover.getTitle()) != null){
            throw new MaterialCoverAlreadyExistException();
        }
        if(materialCover.getId() == 0){
            logService.addLog("Добавлен материал чехла");
        }
        else{
            logService.addLog("Изменен материал чехла");
        }
        materialCoverRepository.save(materialCover);
    }

    public Integer getCountMaterialsCover() { return materialCoverRepository.getCountMaterialsCover(); }
}
