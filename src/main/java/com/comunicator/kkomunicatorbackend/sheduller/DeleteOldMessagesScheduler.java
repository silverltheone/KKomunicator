package com.comunicator.kkomunicatorbackend.sheduller;

import com.comunicator.kkomunicatorbackend.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.stream.Collectors;

@Component
public class DeleteOldMessagesScheduler {

    @Autowired
    private MessageService service;

    @Scheduled(cron = "0 0 10 * * *")
    public void deletingOldMessages() {
        service.getAll().stream()
                .filter(message -> LocalDate.now().minusDays(30).isAfter(message.getSendDate()))
                .collect(Collectors.toList()).stream()
                .forEach(m -> service.delete(m.getId()));
    }
}
