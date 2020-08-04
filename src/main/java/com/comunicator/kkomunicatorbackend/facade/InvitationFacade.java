package com.comunicator.kkomunicatorbackend.facade;

import com.comunicator.kkomunicatorbackend.controller.InvitationNotFoundException;
import com.comunicator.kkomunicatorbackend.controller.UserNotFoundException;
import com.comunicator.kkomunicatorbackend.domain.Invitation;
import com.comunicator.kkomunicatorbackend.domain.Message;
import com.comunicator.kkomunicatorbackend.dto.InvitationDto;
import com.comunicator.kkomunicatorbackend.dto.MessageDto;
import com.comunicator.kkomunicatorbackend.mapper.InvitationMapper;
import com.comunicator.kkomunicatorbackend.service.InvitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InvitationFacade {

    @Autowired
    private InvitationService service;

    @Autowired
    private InvitationMapper mapper;

    public List<InvitationDto> getAll() {
        List<Invitation> invitations = service.getAll();
        return mapper.mapToInvitationDtoList(invitations);
    }

    public InvitationDto get(Long id) throws InvitationNotFoundException {
        Invitation invitation = service.getOne(id);
        return mapper.mapToInvitationDto(invitation);
    }

    public InvitationDto create(InvitationDto invitationDto) throws UserNotFoundException {
        Invitation invitation = service.create(mapper.mapToInvitation(invitationDto));
        return mapper.mapToInvitationDto(invitation);
    }

    public InvitationDto update(InvitationDto invitationDto) throws UserNotFoundException {
        Invitation invitation = service.update(mapper.mapToInvitation(invitationDto));
        return mapper.mapToInvitationDto(invitation);
    }

    public boolean delete(Long id) {
        return service.delete(id);
    }

    public List<InvitationDto> getInvitationBySenderOrReceiverId(Long id) {
        List<Invitation> invitations = service.getInvitationBySenderOrReceiverId(id);
        return mapper.mapToInvitationDtoList(invitations);
    }
}
