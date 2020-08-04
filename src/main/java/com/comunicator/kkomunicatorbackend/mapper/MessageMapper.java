package com.comunicator.kkomunicatorbackend.mapper;

import com.comunicator.kkomunicatorbackend.controller.UserNotFoundException;
import com.comunicator.kkomunicatorbackend.domain.Message;
import com.comunicator.kkomunicatorbackend.dto.MessageDto;
import com.comunicator.kkomunicatorbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MessageMapper {

    @Autowired
    UserService userService;

    public Message mapToMessage(final MessageDto messageDto) throws UserNotFoundException {
        Message message = new Message();
        message.setId(messageDto.getId());
        message.setSender(userService.getOne(messageDto.getSenderId()));
        message.setReceiver(userService.getOne(messageDto.getReceiverId()));
        message.setSendDate(messageDto.getSendDate());
        message.setMessage(messageDto.getMessage());
        message.setWasRead(messageDto.isWasRead());
        return message;
    }

    public MessageDto mapToMessageDto(final Message message) {
        MessageDto messageDto = new MessageDto();
        messageDto.setId(message.getId());
        messageDto.setSenderId(message.getSender().getId());
        messageDto.setReceiverId(message.getReceiver().getId());
        messageDto.setSendDate(message.getSendDate());
        messageDto.setMessage(message.getMessage());
        messageDto.setWasRead(message.isWasRead());
        return messageDto;
    }

    public List<MessageDto> mapToMessageDtoList(final List<Message> messageList) {
        return messageList.stream()
                .map(m -> new MessageDto(
                        m.getId(),
                        m.getSender().getId(),
                        m.getReceiver().getId(),
                        m.getSendDate(),
                        m.getMessage(),
                        m.isWasRead()
                ))
                .collect(Collectors.toList());
    }
}
