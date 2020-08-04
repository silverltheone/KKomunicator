package com.comunicator.kkomunicatorbackend.controller;

import com.comunicator.kkomunicatorbackend.dto.InfoLogDto;
import com.comunicator.kkomunicatorbackend.facade.InfoLogFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/infoLogs")
public class InfoLogController {

    @Autowired
    private InfoLogFacade facade;

    @GetMapping
    public List<InfoLogDto> getAll() {
        return facade.getAll();
    }

    @GetMapping("/{id}")
    public InfoLogDto get(@PathVariable Long id) throws InfoLogNotFoundException {
        return facade.get(id);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public InfoLogDto create(@RequestBody InfoLogDto infoLogDto) throws UserNotFoundException {
        return facade.create(infoLogDto);
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    public InfoLogDto update(@RequestBody InfoLogDto infoLogDto) throws UserNotFoundException {
        return facade.update(infoLogDto);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) throws InfoLogNotFoundException {
        return facade.delete(id);
    }
}
