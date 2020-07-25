package com.comunicator.kkomunicatorbackend.repository;

import com.comunicator.kkomunicatorbackend.domain.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends CrudRepository<Message, Long> {

    @Override
    List<Message> findAll();

    @Override
    Optional<Message> findById(Long id);

    @Override
    Message save(Message message);

    @Override
    void deleteById(Long id);

    @Override
    long count();
}
