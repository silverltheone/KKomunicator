package com.comunicator.kkomunicatorbackend.facade;

import com.comunicator.kkomunicatorbackend.domain.Message;
import com.comunicator.kkomunicatorbackend.domain.User;
import com.comunicator.kkomunicatorbackend.dto.MessageDto;
import com.comunicator.kkomunicatorbackend.mapper.MessageMapper;
import com.comunicator.kkomunicatorbackend.service.MessageService;
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
public class MessageFacadeTest {

    @InjectMocks
    private MessageFacade facade;

    @Mock
    private MessageService service;

    @Mock
    private MessageMapper mapper;

    @Test
    public void shouldFetchMessageList() {
        //Given
        List<Message> messages = new ArrayList<>();
        messages.add(new Message(1L, new User(), new User(), LocalDate.now(), "testing", false));
        List<MessageDto> messageDtos = new ArrayList<>();
        messageDtos.add(new MessageDto(1L, 1L, 2L, LocalDate.now(), "testing", false));

        Mockito.when(service.getAll()).thenReturn(messages);
        Mockito.when(mapper.mapToMessageDtoList(messages)).thenReturn(messageDtos);
        //When
        List<MessageDto> testedList = facade.getAll();
        //Then
        Assert.assertNotNull(testedList);
        Assert.assertEquals(1, testedList.size());
    }

    @Test
    public void shouldFetchMessage() throws Exception {
        //Given
        Message message = new Message(1L, new User(), new User(), LocalDate.now(), "testing", false);
        MessageDto messageDto = new MessageDto(1L, 1L, 2L, LocalDate.now(), "testing", false);

        Mockito.when(service.getOne(1L)).thenReturn(message);
        Mockito.when(mapper.mapToMessageDto(message)).thenReturn(messageDto);

        //When
        MessageDto testedObject = facade.get(1L);
        //Then
        Assert.assertEquals("testing", testedObject.getMessage());
    }

    @Test
    public void shouldCreateMessage()throws Exception {
        //Given
        Message message = new Message(1L, new User(), new User(), LocalDate.now(), "testing", false);
        MessageDto messageDto = new MessageDto(1L, 1L, 2L, LocalDate.now(), "testing", false);

        Mockito.when(service.create(message)).thenReturn(message);
        Mockito.when(mapper.mapToMessageDto(message)).thenReturn(messageDto);
        Mockito.when(mapper.mapToMessage(messageDto)).thenReturn(message);

        //When
        MessageDto testedObject = facade.create(messageDto);
        //Then
        Assert.assertEquals("testing", testedObject.getMessage());
    }

    @Test
    public void shouldUpdateMessage() throws Exception {
        //Given
        Message message = new Message(1L, new User(), new User(), LocalDate.now(), "testing", false);
        MessageDto messageDto = new MessageDto(1L, 1L, 2L, LocalDate.now(), "testing", false);

        Mockito.when(service.update(message)).thenReturn(message);
        Mockito.when(mapper.mapToMessageDto(message)).thenReturn(messageDto);
        Mockito.when(mapper.mapToMessage(messageDto)).thenReturn(message);

        //When
        MessageDto testedObject = facade.update(messageDto);
        //Then
        Assert.assertEquals("testing", testedObject.getMessage());
    }

    @Test
    public void shouldDeleteMessage()throws Exception {
        //Given
        Message message = new Message(1L, new User(), new User(), LocalDate.now(), "testing", false);

        Mockito.when(service.delete(1L)).thenReturn(true);
        //When
        Boolean result = facade.delete(1L);
        //Then
        Assert.assertTrue(result);
    }
}