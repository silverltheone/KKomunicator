package com.comunicator.kkomunicatorbackend.controller;


import com.comunicator.kkomunicatorbackend.domain.LocData;
import com.comunicator.kkomunicatorbackend.service.LocalisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/localisation")
public class LocalisationController {

    @Autowired
    private LocalisationService service;

    @GetMapping("/{ip}")
    public LocData get(@PathVariable String ip) {
        return service.get(ip);
    }
}
