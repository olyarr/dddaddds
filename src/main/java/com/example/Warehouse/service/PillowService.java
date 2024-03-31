package com.example.Warehouse.service;

import com.example.Warehouse.model.Pillow;
import com.example.Warehouse.repository.PillowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PillowService {
    private final PillowRepository pillowRepository;

    private final LogService logService;

    @Autowired
    public PillowService(PillowRepository pillowRepository, LogService logService) {
        this.pillowRepository = pillowRepository;
        this.logService = logService;
    }

    public List<Pillow> getPillows(String title) {
        if (!title.isEmpty()) { return pillowRepository.findByTitle(title); }
        return pillowRepository.findAll();
    }

    public Pillow getPillow(int id) { return pillowRepository.findById(id).orElse(null); }

    @Transactional
    public void addOrUpdatePillow(Pillow pillow) {
        if(pillow.getId() == 0){
            logService.addLog("Добавлен материал наполнителя");
        }
        else{
            logService.addLog("Изменен материал наполнителя");
        }
        pillowRepository.save(pillow);
    }

    @Transactional
    public void deletePillow(int id) {
        pillowRepository.deleteById(id);
        logService.addLog("Удален материал наполнителя");
    }

    public Integer getCountPillows() { return pillowRepository.getCountPillows(); }
}