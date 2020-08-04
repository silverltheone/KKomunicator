package com.comunicator.kkomunicatorbackend.repository;

import com.comunicator.kkomunicatorbackend.domain.Invitation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InvitationRepository extends CrudRepository<Invitation, Long> {

    @Override
    List<Invitation> findAll();

    @Override
    Optional<Invitation> findById(Long id);

    @Override
    Invitation save(Invitation invitation);

    @Override
    void deleteById(Long id);

    @Override
    long count();

    @Query("select c from INVITATIONS c " +
            "where c.sender.id=:id " +
            "or c.receiver.id=:id")
    List<Invitation> getInvitationBySenderOrReceiverId(@Param("id") Long id);
}
