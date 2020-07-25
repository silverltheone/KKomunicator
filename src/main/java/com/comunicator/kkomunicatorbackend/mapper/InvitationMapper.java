package com.comunicator.kkomunicatorbackend.mapper;

import com.comunicator.kkomunicatorbackend.domain.Invitation;
import com.comunicator.kkomunicatorbackend.dto.InvitationDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InvitationMapper {

    public Invitation mapToInvitation(final InvitationDto invitationDto) {
        return new Invitation(
                invitationDto.getId(),
                invitationDto.getSender(),
                invitationDto.getReceiver(),
                invitationDto.getSendDate()
        );
    }

    public InvitationDto mapToInvitationDto(final Invitation invitation) {
        return new InvitationDto(
                invitation.getId(),
                invitation.getSender(),
                invitation.getReceiver(),
                invitation.getSendDate()
        );
    }

    public List<Invitation> mapToInvitationList(final List<InvitationDto> invitationDtoList) {
        return invitationDtoList.stream()
                .map(i -> new Invitation(
                        i.getId(),
                        i.getSender(),
                        i.getReceiver(),
                        i.getSendDate()
                ))
                .collect(Collectors.toList());
    }

    public List<InvitationDto> mapToInvitationDtoList(final List<Invitation> invitationList) {
        return invitationList.stream()
                .map(i -> new InvitationDto(
                        i.getId(),
                        i.getSender(),
                        i.getReceiver(),
                        i.getSendDate()
                ))
                .collect(Collectors.toList());
    }
}
