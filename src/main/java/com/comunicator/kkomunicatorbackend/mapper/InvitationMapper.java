package com.comunicator.kkomunicatorbackend.mapper;

import com.comunicator.kkomunicatorbackend.controller.UserNotFoundException;
import com.comunicator.kkomunicatorbackend.domain.Invitation;
import com.comunicator.kkomunicatorbackend.dto.InvitationDto;
import com.comunicator.kkomunicatorbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InvitationMapper {

    @Autowired
    UserService userService;

    public Invitation mapToInvitation(final InvitationDto invitationDto) throws UserNotFoundException {
        Invitation invitation = new Invitation();
        invitation.setId(invitationDto.getId());
        invitation.setSender(userService.getOne(invitationDto.getSenderId()));
        invitation.setReceiver(userService.getOne(invitationDto.getReceiverId()));
        invitation.setSendDate(invitationDto.getSendDate());
        invitation.setWasRead(invitationDto.isWasRead());
        return invitation;
    }

    public InvitationDto mapToInvitationDto(final Invitation invitation) {
        InvitationDto invitationDto = new InvitationDto();
        invitationDto.setId(invitation.getId());
        invitationDto.setSenderId(invitation.getSender().getId());
        invitationDto.setReceiverId(invitation.getReceiver().getId());
        invitationDto.setSendDate(invitation.getSendDate());
        invitationDto.setWasRead(invitation.isWasRead());
        return invitationDto;
    }

    public List<InvitationDto> mapToInvitationDtoList(final List<Invitation> invitationList) {
        return invitationList.stream()
                .map(i -> new InvitationDto(
                        i.getId(),
                        i.getSender().getId(),
                        i.getReceiver().getId(),
                        i.getSendDate(),
                        i.isWasRead()
                ))
                .collect(Collectors.toList());
    }
}
