package com.comunicator.kkomunicatorbackend.service;

import com.comunicator.kkomunicatorbackend.controller.InvitationNotFoundException;
import com.comunicator.kkomunicatorbackend.domain.Invitation;
import com.comunicator.kkomunicatorbackend.domain.User;
import com.comunicator.kkomunicatorbackend.repository.InvitationRepository;
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
public class InvitationServiceTest {

    @InjectMocks
    private InvitationService service;

    @Mock
    private InvitationRepository repository;

    @Test
    public void testGelAllInvitations() {
        //Given
        List<Invitation> invitations = new ArrayList<>();
        invitations.add(new Invitation(1L, new User(), new User(), LocalDate.now(), false));
        Mockito.when(repository.findAll()).thenReturn(invitations);
        //When
        List<Invitation> testedList = service.getAll();
        //Then
        Assert.assertEquals(1, testedList.size());
        Assert.assertEquals(false, testedList.get(0).isWasRead());
    }

    @Test
    public void testGetInvitation() throws InvitationNotFoundException {
        //Given
        Invitation invitation = new Invitation(1L, new User(), new User(), LocalDate.now(), false);
        Mockito.when(repository.findById(1l)).thenReturn(java.util.Optional.of(invitation));
        //When
        Invitation testedInvitation = service.getOne(1L);
        //Then
        Assert.assertNotNull(testedInvitation);
        Assert.assertEquals(LocalDate.now(), testedInvitation.getSendDate());
    }

    @Test
    public void testUpdateInvitation() {
        //Given
        Invitation invitation = new Invitation(1L, new User(), new User(), LocalDate.now(), false);

        Mockito.when(repository.findById(1L)).thenReturn(java.util.Optional.of(invitation));

        //When
        Invitation updatedInvitation = service.update(invitation);
        //Then
        Assert.assertNotNull(updatedInvitation);
        Assert.assertEquals(updatedInvitation.getId(), invitation.getId());
        Mockito.verify(repository, Mockito.times(1)).save(updatedInvitation);
    }

    @Test
    public void testSaveInvitation() {
        //Given
        Invitation invitation = new Invitation(1L, new User(), new User(), LocalDate.now(), false);

        Mockito.when(repository.save(invitation)).thenReturn(new Invitation(invitation.getId(), invitation.getSender(), invitation.getReceiver(), invitation.getSendDate(), invitation.isWasRead()));
        //When
        Invitation testedInvitation = service.create(invitation);
        //Then
        Mockito.verify(repository, Mockito.times(1)).save(invitation);
    }

    @Test
    public void testDeleteInvitation() {
        //Given
        Invitation invitation = new Invitation(1L, new User(), new User(), LocalDate.now(), false);

        //When
        service.delete(1L);
        //Then
        Mockito.verify(repository, Mockito.times(1)).deleteById(1L);
    }
}