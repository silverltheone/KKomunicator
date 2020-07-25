package com.comunicator.kkomunicatorbackend.controller;

import com.comunicator.kkomunicatorbackend.dto.UserDto;
import com.comunicator.kkomunicatorbackend.facade.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/user")
public class UserController {

    @Autowired
    private UserFacade facade;

    @GetMapping
    public List<UserDto> getAll() {
        return facade.getAll();
    }

    @GetMapping("/{id}")
    public UserDto get(@PathVariable Long id) throws UserNotFoundException{
        return facade.get(id);
    }

    @PostMapping
    public UserDto create(@RequestBody UserDto userDto) {
        return facade.create(userDto);
    }

    @PutMapping
    public UserDto update(@RequestBody UserDto userDto) {
        return facade.update(userDto);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) throws UserNotFoundException {
        return facade.delete(id);
    }
}
