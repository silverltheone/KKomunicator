package com.comunicator.kkomunicatorbackend.service;

import com.comunicator.kkomunicatorbackend.client.LocalisationClient;
import com.comunicator.kkomunicatorbackend.domain.LocData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocalisationService {

    @Autowired
    LocalisationClient client;


    public LocData get(String ip) {
        return client.get(ip);
    }
}
