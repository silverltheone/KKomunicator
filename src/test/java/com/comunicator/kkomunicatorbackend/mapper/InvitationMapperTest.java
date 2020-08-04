package com.comunicator.kkomunicatorbackend.mapper;

import com.comunicator.kkomunicatorbackend.domain.Invitation;
import com.comunicator.kkomunicatorbackend.domain.User;
import com.comunicator.kkomunicatorbackend.dto.InvitationDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class InvitationMapperTest {

    @InjectMocks
    private InvitationMapper mapper;

    @Test
    public void testMapToInvitationDto() {
        //Given
        Invitation invitation = new Invitation(1L, new User(), new User(), LocalDate.now(), false);
        //When
        InvitationDto invitationDto = mapper.mapToInvitationDto(invitation);
        //Then
        Assert.assertEquals(LocalDate.now(), invitationDto.getSendDate());
    }

    @Test
    public void testMapToInvitationDtosList() {
        //Given
        Invitation invitation1 = new Invitation(1L, new User(), new User(), LocalDate.now(), false);

        Invitation invitation2 = new Invitation(2L, new User(), new User(), LocalDate.now(), false);

        List<Invitation> invitations = new ArrayList<>();
        invitations.add(invitation1);
        invitations.add(invitation1);
        //When
        List<InvitationDto> invitationDtos = mapper.mapToInvitationDtoList(invitations);
        //Then
        Assert.assertEquals(2, invitationDtos.size());
        Assert.assertEquals(LocalDate.now(), invitationDtos.get(1).getSendDate());
    }
}