package com.comunicator.kkomunicatorbackend.service;

import com.comunicator.kkomunicatorbackend.controller.InvitationNotFoundException;
import com.comunicator.kkomunicatorbackend.domain.Invitation;
import com.comunicator.kkomunicatorbackend.repository.InvitationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvitationService {

    @Autowired
    private InvitationRepository repository;

    public List<Invitation> getAll() {
        return repository.findAll();
    }

    public Invitation getOne(final Long id) throws InvitationNotFoundException {
        return repository.findById(id).orElseThrow(InvitationNotFoundException::new);
    }

    public Invitation create(Invitation invitation) {
        return repository.save(invitation);
    }

    public Invitation update(Invitation invitation) {
        Optional<Invitation> invitationOptional = repository.findById(invitation.getId());
        if(invitationOptional.isPresent()) {
            Invitation updatedInvitation = invitationOptional.get();
            updatedInvitation.setId(invitation.getId());
            updatedInvitation.setSender(invitation.getSender());
            updatedInvitation.setReceiver(invitation.getReceiver());
            updatedInvitation.setSendDate(invitation.getSendDate());
            repository.save(updatedInvitation);
            return updatedInvitation;
        } else {
            throw new RuntimeException("Cannot find invitation with id: " + invitation.getId());
        }
    }

    public boolean delete(Long id) throws InvitationNotFoundException {
        Invitation invitation = repository.findById(id).orElseThrow(InvitationNotFoundException::new);
        repository.delete(invitation);
        return !repository.existsById(id);
    }
}
