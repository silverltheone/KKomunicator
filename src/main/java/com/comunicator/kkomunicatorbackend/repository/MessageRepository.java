package com.comunicator.kkomunicatorbackend.repository;

import com.comunicator.kkomunicatorbackend.domain.Message;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends CrudRepository<Message, Long> {

    @Override
    List<Message> findAll();

    @Override
    List<Message> findAllById(Iterable<Long> idList);

    @Override
    Optional<Message> findById(Long id);

    @Query("select c from MESSAGES c " +
            "where c.sender.id=:id " +
            "or c.receiver.id=:id")
    List<Message> getEmailBySenderOrReceiverId(@Param("id") Long id);

    @Override
    Message save(Message message);

    @Override
    void deleteById(Long id);

    @Override
    long count();
}
