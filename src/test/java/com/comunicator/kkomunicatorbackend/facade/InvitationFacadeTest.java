package com.comunicator.kkomunicatorbackend.facade;

import com.comunicator.kkomunicatorbackend.domain.Invitation;
import com.comunicator.kkomunicatorbackend.domain.User;
import com.comunicator.kkomunicatorbackend.dto.InvitationDto;
import com.comunicator.kkomunicatorbackend.mapper.InvitationMapper;
import com.comunicator.kkomunicatorbackend.service.InvitationService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class InvitationFacadeTest {

    @InjectMocks
    private InvitationFacade facade;

    @Mock
    private InvitationService service;

    @Mock
    private InvitationMapper mapper;

    @Test
    public void shouldFetchInvitationList() {
        //Given
        List<Invitation> invitations = new ArrayList<>();
        invitations.add(new Invitation(1L, new User(), new User(), LocalDate.now(), false));
        List<InvitationDto> invitationDtos = new ArrayList<>();
        invitationDtos.add(new InvitationDto(1L, 1L, 2L, LocalDate.now(), false));

        Mockito.when(service.getAll()).thenReturn(invitations);
        Mockito.when(mapper.mapToInvitationDtoList(invitations)).thenReturn(invitationDtos);
        //When
        List<InvitationDto> testedList = facade.getAll();
        //Then
        Assert.assertNotNull(testedList);
        Assert.assertEquals(1, testedList.size());
    }

    @Test
    public void shouldFetchIvitation() throws Exception {
        //Given
        Invitation invitation = new Invitation(1L, new User(), new User(), LocalDate.now(), false);
        InvitationDto invitationDto = new InvitationDto(1L, 1L, 2L, LocalDate.now(), false);

        Mockito.when(service.getOne(1L)).thenReturn(invitation);
        Mockito.when(mapper.mapToInvitationDto(invitation)).thenReturn(invitationDto);

        //When
        InvitationDto testedObject = facade.get(1L);
        //Then
        Assert.assertEquals(false, testedObject.isWasRead());
    }

    @Test
    public void shouldCreateInvitation()throws Exception {
        //Given
        Invitation invitation = new Invitation(1L, new User(), new User(), LocalDate.now(), false);
        InvitationDto invitationDto = new InvitationDto(1L, 1L, 2L, LocalDate.now(), false);

        Mockito.when(service.create(invitation)).thenReturn(invitation);
        Mockito.when(mapper.mapToInvitationDto(invitation)).thenReturn(invitationDto);
        Mockito.when(mapper.mapToInvitation(invitationDto)).thenReturn(invitation);

        //When
        InvitationDto testedObject = facade.create(invitationDto);
        //Then
        Assert.assertEquals(false, testedObject.isWasRead());
    }

    @Test
    public void shouldUpdateInvitation() throws Exception {
        //Given
        Invitation invitation = new Invitation(1L, new User(), new User(), LocalDate.now(), false);
        InvitationDto invitationDto = new InvitationDto(1L, 1L, 2L, LocalDate.now(), false);

        Mockito.when(service.update(invitation)).thenReturn(invitation);
        Mockito.when(mapper.mapToInvitationDto(invitation)).thenReturn(invitationDto);
        Mockito.when(mapper.mapToInvitation(invitationDto)).thenReturn(invitation);

        //When
        InvitationDto testedObject = facade.update(invitationDto);
        //Then
        Assert.assertEquals(false, testedObject.isWasRead());
    }

    @Test
    public void shouldDeleteInvitation()throws Exception {
        //Given
        Invitation invitation = new Invitation(1L, new User(), new User(), LocalDate.now(), false);

        Mockito.when(service.delete(1L)).thenReturn(true);
        //When
        Boolean result = facade.delete(1L);
        //Then
        Assert.assertTrue(result);
    }
}