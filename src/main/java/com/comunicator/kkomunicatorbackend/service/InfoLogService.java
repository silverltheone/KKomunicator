package com.comunicator.kkomunicatorbackend.service;

import com.comunicator.kkomunicatorbackend.controller.InfoLogNotFoundException;
import com.comunicator.kkomunicatorbackend.domain.InfoLog;
import com.comunicator.kkomunicatorbackend.repository.InfoLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InfoLogService {

    @Autowired
    private InfoLogRepository repository;

    public List<InfoLog> getAll() {
        return repository.findAll();
    }

    public InfoLog getOne(final Long id) throws InfoLogNotFoundException {
        return repository.findById(id).orElseThrow(InfoLogNotFoundException::new);
    }

    public InfoLog create(InfoLog infoLog) {
        return repository.save(infoLog);
    }

    public InfoLog update(InfoLog infoLog) {
        Optional<InfoLog> infoLogOptional = repository.findById(infoLog.getId());
        if(infoLogOptional.isPresent()) {
            InfoLog updatedInfoLog = infoLogOptional.get();
            updatedInfoLog.setId(infoLog.getId());
            updatedInfoLog.setUser(infoLog.getUser());
            updatedInfoLog.setDate(infoLog.getDate());
            updatedInfoLog.setType(infoLog.getType());
            repository.save(updatedInfoLog);
            return updatedInfoLog;
        } else {
            throw new RuntimeException("Cannot find invitation with id: " + infoLog.getId());
        }
    }

    public boolean delete(Long id) {
        repository.deleteById(id);
        return !repository.existsById(id);
    }
}
