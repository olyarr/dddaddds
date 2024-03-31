package com.example.Warehouse.service;

import com.example.Warehouse.model.Log;
import com.example.Warehouse.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LogService {
    private final LogRepository logRepository;

    @Autowired
    public LogService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public void addLog(String action) {
        Log log = new Log();
        log.setDateTime(LocalDateTime.now());
        log.setAction(action);
        logRepository.save(log);
    }
}
