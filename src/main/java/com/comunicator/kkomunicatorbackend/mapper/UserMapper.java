package com.comunicator.kkomunicatorbackend.mapper;

import com.comunicator.kkomunicatorbackend.domain.User;
import com.comunicator.kkomunicatorbackend.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public User mapToUser(UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail(),
                userDto.getPassword(),
                userDto.getFriends(),
                userDto.getFriendOf(),
                userDto.getSentMessages(),
                userDto.getReceivedMessages()
        );
    }

    public UserDto mapToUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                user.getFriends(),
                user.getFriendOf(),
                user.getSentMessages(),
                user.getReceivedMessages()
        );
    }

    public List<User> mapToUserList(List<UserDto> userDtoList) {
        return userDtoList.stream()
                .map(u -> new User(
                        u.getId(),
                        u.getFirstName(),
                        u.getLastName(),
                        u.getEmail(),
                        u.getPassword(),
                        u.getFriends(),
                        u.getFriendOf(),
                        u.getSentMessages(),
                        u.getReceivedMessages()
                        ))
                .collect(Collectors.toList());
    }

    public List<UserDto> mapToUserDtoList(List<User> userList) {
        return userList.stream()
                .map(u -> new UserDto(
                        u.getId(),
                        u.getFirstName(),
                        u.getLastName(),
                        u.getEmail(),
                        u.getPassword(),
                        u.getFriends(),
                        u.getFriendOf(),
                        u.getSentMessages(),
                        u.getReceivedMessages()
                ))
                .collect(Collectors.toList());
    }
}
