package com.comunicator.kkomunicatorbackend.service;

import com.comunicator.kkomunicatorbackend.controller.MessageNotFoundException;
import com.comunicator.kkomunicatorbackend.domain.Message;
import com.comunicator.kkomunicatorbackend.domain.User;
import com.comunicator.kkomunicatorbackend.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    private MessageRepository repository;

    public List<Message> getAll() {
        return repository.findAll();
    }

    public List<Message> getAllById(List<Long> messagesId) {
        return repository.findAllById(messagesId);
    }

    public Message getOne(final Long id) throws MessageNotFoundException {
        return repository.findById(id).orElseThrow(MessageNotFoundException::new);
    }

    public List<Message> getEmailBySenderOrReceiverId(Long id) {
        return repository.getEmailBySenderOrReceiverId(id);
    }

    public Message create(Message message) {
        return repository.save(message);
    }

    public Message update(Message message) {
        Optional<Message> messageOptional = repository.findById(message.getId());
        if(messageOptional.isPresent()) {
            Message updatedMessage = messageOptional.get();
            updatedMessage.setId(message.getId());
            updatedMessage.setSender(message.getSender());
            updatedMessage.setReceiver(message.getReceiver());
            updatedMessage.setSendDate(message.getSendDate());
            updatedMessage.setMessage(message.getMessage());
            updatedMessage.setWasRead(message.isWasRead());
            repository.save(updatedMessage);
            return updatedMessage;
        } else {
            throw new RuntimeException("Cannot find message with id: " + message.getId());
        }
    }

    public boolean delete(Long id)  {
        repository.deleteById(id);
        return !repository.existsById(id);
    }
}
