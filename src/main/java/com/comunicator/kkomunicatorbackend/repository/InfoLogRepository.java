package com.comunicator.kkomunicatorbackend.repository;

import com.comunicator.kkomunicatorbackend.domain.InfoLog;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface InfoLogRepository extends CrudRepository<InfoLog, Long> {

    @Override
    List<InfoLog> findAll();

    @Override
    Optional<InfoLog> findById(Long id);

    @Override
    InfoLog save(InfoLog infoLog);

    @Override
    void deleteById(Long id);

    @Override
    long count();
}
