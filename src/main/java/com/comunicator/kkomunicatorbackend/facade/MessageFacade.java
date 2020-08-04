package com.comunicator.kkomunicatorbackend.facade;

import com.comunicator.kkomunicatorbackend.controller.MessageNotFoundException;
import com.comunicator.kkomunicatorbackend.controller.UserNotFoundException;
import com.comunicator.kkomunicatorbackend.domain.Message;
import com.comunicator.kkomunicatorbackend.dto.MessageDto;
import com.comunicator.kkomunicatorbackend.mapper.MessageMapper;
import com.comunicator.kkomunicatorbackend.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MessageFacade {

    @Autowired
    private MessageService service;

    @Autowired
    private MessageMapper mapper;

    public List<MessageDto> getAll() {
        List<Message> messages = service.getAll();
        return mapper.mapToMessageDtoList(messages);
    }

    public MessageDto get(Long id) throws MessageNotFoundException {
        Message message =  service.getOne(id);
        return mapper.mapToMessageDto(message);
    }

    public List<MessageDto> getEmailBySenderOrReceiverId(Long id)  {
        List<Message> messages = service.getEmailBySenderOrReceiverId(id);
        return mapper.mapToMessageDtoList(messages);
    }

    public MessageDto create(MessageDto messageDto) throws UserNotFoundException {
        Message message = service.create(mapper.mapToMessage(messageDto));
        return mapper.mapToMessageDto(message);
    }

    public MessageDto update(MessageDto messageDto) throws UserNotFoundException {
        Message message = service.update(mapper.mapToMessage(messageDto));
        return mapper.mapToMessageDto(message);
    }

    public boolean delete(Long id) {
        return service.delete(id);
    }
}
