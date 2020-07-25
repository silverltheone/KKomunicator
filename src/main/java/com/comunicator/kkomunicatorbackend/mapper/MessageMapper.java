package com.comunicator.kkomunicatorbackend.mapper;

import com.comunicator.kkomunicatorbackend.domain.Message;
import com.comunicator.kkomunicatorbackend.dto.MessageDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MessageMapper {

    public Message mapToMessage(final MessageDto messageDto) {
        return new Message(
                messageDto.getId(),
                messageDto.getSender(),
                messageDto.getReceiver(),
                messageDto.getSendDate(),
                messageDto.getMessage(),
                messageDto.isWasRead()
        );
    }

    public MessageDto mapToMessageDto(final Message message) {
        return new MessageDto(
                message.getId(),
                message.getSender(),
                message.getReceiver(),
                message.getSendDate(),
                message.getMessage(),
                message.isWasRead()
        );
    }

    public List<Message> mapToMessageList(final List<MessageDto> messageDtoList) {
        return messageDtoList.stream()
                .map(m -> new Message(
                        m.getId(),
                        m.getSender(),
                        m.getReceiver(),
                        m.getSendDate(),
                        m.getMessage(),
                        m.isWasRead()
                        ))
                .collect(Collectors.toList());
    }

    public List<MessageDto> mapToMessageDtoList(final List<Message> messageList) {
        return messageList.stream()
                .map(m -> new MessageDto(
                        m.getId(),
                        m.getSender(),
                        m.getReceiver(),
                        m.getSendDate(),
                        m.getMessage(),
                        m.isWasRead()
                ))
                .collect(Collectors.toList());
    }
}
