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

    @GetMapping(value = "/getByEmailAndPassword/{email}/{password}")
    public UserDto getByEmailAndPassword(@PathVariable String email, @PathVariable String password) throws UserNotFoundException{
        return facade.getByEmailAndPassword(email, password);
    }

    @GetMapping(value = "/getByEmail/{email}")
    public UserDto getByEmail(@PathVariable String email) throws UserNotFoundException{
        return facade.getByEmail(email);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public UserDto create(@RequestBody UserDto userDto) throws UserNotFoundException {
        return facade.create(userDto);
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    public UserDto update(@RequestBody UserDto userDto) throws UserNotFoundException {
        return facade.update(userDto);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) throws UserNotFoundException {
        return facade.delete(id);
    }
}
