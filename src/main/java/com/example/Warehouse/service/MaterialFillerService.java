package com.example.Warehouse.service;

import com.example.Warehouse.exception.MaterialFillerAlreadyExistException;
import com.example.Warehouse.model.MaterialFiller;
import com.example.Warehouse.repository.MaterialFillerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MaterialFillerService {
    private final MaterialFillerRepository materialFillerRepository;

    private final LogService logService;

    @Autowired
    public MaterialFillerService(MaterialFillerRepository materialFillerRepository, LogService logService) {
        this.materialFillerRepository = materialFillerRepository;
        this.logService = logService;
    }

    public List<MaterialFiller> getMaterialsFiller() {
        return (List<MaterialFiller>) materialFillerRepository.findAll();
    }

    public MaterialFiller getMaterialFiller(int id) {
        return materialFillerRepository.findById(id).orElse(null);
    }

    @Transactional
    public void addOrUpdateMaterialFiller(MaterialFiller materialFiller) throws MaterialFillerAlreadyExistException {
        if(materialFillerRepository.findByTitle(materialFiller.getTitle()) != null){
            throw new MaterialFillerAlreadyExistException();
        }
        if(materialFiller.getId() == 0){
            logService.addLog("Добавлен материал наполнителя");
        }
        else{
            logService.addLog("Изменен материал наполнителя");
        }
        materialFillerRepository.save(materialFiller);
    }

    public Integer getCountMaterialsFiller() { return materialFillerRepository.getCountMaterialsFiller(); }
}
