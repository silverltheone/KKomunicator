package com.comunicator.kkomunicatorbackend.client;

import com.comunicator.kkomunicatorbackend.domain.LocData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class LocalisationClient {

    @Autowired
    RestTemplate restTemplate;

    public LocData get(String ip) {
        URI getILocalisationUrl = UriComponentsBuilder.fromHttpUrl("http://ip-api.com/json/" + ip).build().encode().toUri();
        LocData locData = restTemplate.getForObject(getILocalisationUrl, LocData.class);
        return locData;
    }
}
