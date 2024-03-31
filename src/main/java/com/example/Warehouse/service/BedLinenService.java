package com.example.Warehouse.service;

import com.example.Warehouse.model.BedLinen;
import com.example.Warehouse.repository.BedLinenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class BedLinenService {
    private final BedLinenRepository bedLinenRepository;

    private final LogService logService;

    @Autowired
    public BedLinenService(BedLinenRepository bedLinenRepository, LogService logService) {
        this.bedLinenRepository = bedLinenRepository;
        this.logService = logService;
    }

    public List<BedLinen> getBedLinens(String title) {
        if (!title.isEmpty()) { return bedLinenRepository.findByTitle(title); }
        return (List<BedLinen>) bedLinenRepository.findAll();
    }

    public BedLinen getBedLinen(int id) { return bedLinenRepository.findById(id).orElse(null); }

    @Transactional
    public void addOrUpdateBedLinen(BedLinen bedLinen) {
        if(bedLinen.getId() == 0){
            logService.addLog("Добавлено постельное белье");
        }
        else{
            logService.addLog("Изменено постельное белье");
        }
        bedLinenRepository.save(bedLinen);
    }

    @Transactional
    public void deleteBedLinen(int id){
        bedLinenRepository.deleteById(id);
        logService.addLog("Удалено постельное белье");
    }

    public Integer getCountBedLinens() { return bedLinenRepository.getCountBedLinens(); }
}