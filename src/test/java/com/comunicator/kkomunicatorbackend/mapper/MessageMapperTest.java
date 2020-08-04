package com.comunicator.kkomunicatorbackend.mapper;

import com.comunicator.kkomunicatorbackend.domain.Invitation;
import com.comunicator.kkomunicatorbackend.domain.Message;
import com.comunicator.kkomunicatorbackend.domain.User;
import com.comunicator.kkomunicatorbackend.dto.InvitationDto;
import com.comunicator.kkomunicatorbackend.dto.MessageDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class MessageMapperTest {

    @InjectMocks
    private MessageMapper mapper;

    @Test
    public void testMapToMessageDto() {
        //Given
        Message message = new Message(1L, new User(), new User(), LocalDate.now(),"TESTING", false);
        //When
        MessageDto messageDto = mapper.mapToMessageDto(message);
        //Then
        Assert.assertEquals("TESTING", messageDto.getMessage());
    }

    @Test
    public void testMapToMessageDtosList() {
        //Given
        Message message1 = new Message(1L, new User(), new User(), LocalDate.now(),"TESTING1", false);

        Message message2 = new Message(2L, new User(), new User(), LocalDate.now(),"TESTING2", false);

        List<Message> messages = new ArrayList<>();
        messages.add(message1);
        messages.add(message2);
        //When
        List<MessageDto> messageDtos = mapper.mapToMessageDtoList(messages);
        //Then
        Assert.assertEquals(2, messageDtos.size());
        Assert.assertEquals("TESTING2", messageDtos.get(1).getMessage());
    }
}