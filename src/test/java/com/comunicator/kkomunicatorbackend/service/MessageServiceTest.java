package com.comunicator.kkomunicatorbackend.service;

import com.comunicator.kkomunicatorbackend.controller.MessageNotFoundException;
import com.comunicator.kkomunicatorbackend.domain.Message;
import com.comunicator.kkomunicatorbackend.domain.User;
import com.comunicator.kkomunicatorbackend.repository.MessageRepository;
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
public class MessageServiceTest {

    @InjectMocks
    private MessageService service;

    @Mock
    private MessageRepository repository;

    @Test
    public void testGelAllMessages() {
        //Given
        List<Message> messages = new ArrayList<>();
        messages.add(new Message(1L, new User(), new User(), LocalDate.now(),"Testing", false));
        Mockito.when(repository.findAll()).thenReturn(messages);
        //When
        List<Message> testedList = service.getAll();
        //Then
        Assert.assertEquals(1, testedList.size());
        Assert.assertEquals("Testing", testedList.get(0).getMessage());
    }

    @Test
    public void testGetMessage() throws MessageNotFoundException {
        //Given
        Message message = new Message(1L, new User(), new User(), LocalDate.now(),"Testing", false);
        Mockito.when(repository.findById(1l)).thenReturn(java.util.Optional.of(message));
        //When
        Message testedMessage = service.getOne(1L);
        //Then
        Assert.assertNotNull(testedMessage);
        Assert.assertEquals("Testing", testedMessage.getMessage());
    }

    @Test
    public void testUpdateMessage() {
        //Given
        Message message = new Message(1L, new User(), new User(), LocalDate.now(),"Testing", false);

        Mockito.when(repository.findById(1L)).thenReturn(java.util.Optional.of(message));

        //When
        Message updatedMessage = service.update(message);
        //Then
        Assert.assertNotNull(updatedMessage);
        Assert.assertEquals(updatedMessage.getId(), message.getId());
        Mockito.verify(repository, Mockito.times(1)).save(updatedMessage);
    }

    @Test
    public void testSaveMessage() {
        //Given
        Message message = new Message(1L, new User(), new User(), LocalDate.now(),"Testing", false);

        Mockito.when(repository.save(message)).thenReturn(new Message(message.getId(), message.getSender(), message.getReceiver(), message.getSendDate(), message.getMessage(), message.isWasRead()));
        //When
        Message testedMessage = service.create(message);
        //Then
        Mockito.verify(repository, Mockito.times(1)).save(message);
    }

    @Test
    public void testDeleteMessage() {
        //Given
        Message message = new Message(1L, new User(), new User(), LocalDate.now(),"Testing", false);

        //When
        service.delete(1L);
        //Then
        Mockito.verify(repository, Mockito.times(1)).deleteById(1L);
    }
}