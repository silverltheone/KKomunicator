package com.comunicator.kkomunicatorbackend.facade;

import com.comunicator.kkomunicatorbackend.controller.UserNotFoundException;
import com.comunicator.kkomunicatorbackend.domain.User;
import com.comunicator.kkomunicatorbackend.dto.UserDto;
import com.comunicator.kkomunicatorbackend.mapper.UserMapper;
import com.comunicator.kkomunicatorbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.List;

@EnableAspectJAutoProxy
@Component
public class UserFacade {

    @Autowired
    UserService service;

    @Autowired
    UserMapper mapper;

    public List<UserDto> getAll() {
        List<User> users =  service.getAll();
        return mapper.mapToUserDtoList(users);
    }

    public UserDto get(Long id) throws UserNotFoundException {
        User user = service.getOne(id);
        return mapper.mapToUserDto(user);
    }

    public UserDto getByEmailAndPassword(String email, String password) throws UserNotFoundException {
        User user = service.getByEmailAndPassword(email, password);
        return mapper.mapToUserDto(user);
    }

    public UserDto getByEmail(String email) throws  UserNotFoundException {
        User user = service.getByEmail(email);
        return mapper.mapToUserDto(user);
    }

    public UserDto create(UserDto userDto) {
        User user = service.create(mapper.mapToUser(userDto));
        return mapper.mapToUserDto(user);
    }

    public UserDto update(UserDto userDto) {
        User user = service.update(mapper.mapToUser(userDto));
        return mapper.mapToUserDto(user);
    }

    public boolean delete(Long id)  {
        return service.delete(id);
    }
}
