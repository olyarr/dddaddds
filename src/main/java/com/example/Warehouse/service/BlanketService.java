package com.example.Warehouse.service;

import com.example.Warehouse.model.Blanket;
import com.example.Warehouse.repository.BlanketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BlanketService {
    private final BlanketRepository blanketRepository;

    private final LogService logService;

    @Autowired
    public BlanketService(BlanketRepository blanketRepository, LogService logService) { this.blanketRepository = blanketRepository;
        this.logService = logService;
    }

    public List<Blanket> getBlankets(String title) {
        if (!title.isEmpty()) { return blanketRepository.findByTitle(title); }
        return blanketRepository.findAll();
    }

    public Blanket getBlanket(int id) { return blanketRepository.findById(id).orElse(null); }

    @Transactional
    public void addOrUpdateBlanket(Blanket blanket) {
        if(blanket.getId() == 0){
            logService.addLog("Добавлено одеяло");
        }
        else{
            logService.addLog("Изменено одеяло");
        }
        blanketRepository.save(blanket);
    }

    @Transactional
    public void deleteBlanket(int id) {
        blanketRepository.deleteById(id);
        logService.addLog("Удалено одеяло");
    }

    public Integer getCountBlankets() { return blanketRepository.getCountBlankets(); }
}