package com.comunicator.kkomunicatorbackend.controller;

import com.comunicator.kkomunicatorbackend.dto.MessageDto;
import com.comunicator.kkomunicatorbackend.facade.MessageFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/message")
public class MessageController {

    @Autowired
    private MessageFacade facade;

    @GetMapping
    public List<MessageDto> getAll() {
        return facade.getAll();
    }

    @GetMapping("/{id}")
    public MessageDto get(@PathVariable Long id) throws MessageNotFoundException {
        return facade.get(id);
    }

    @PostMapping
    public MessageDto create(@RequestBody MessageDto messageDto) {
        return facade.create(messageDto);
    }

    @PutMapping
    public MessageDto update(@RequestBody MessageDto messageDto) {
        return facade.update(messageDto);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) throws MessageNotFoundException{
        return facade.delete(id);
    }
}
