package com.comunicator.kkomunicatorbackend.mapper;

import com.comunicator.kkomunicatorbackend.domain.User;
import com.comunicator.kkomunicatorbackend.dto.UserDto;
import com.comunicator.kkomunicatorbackend.service.MessageService;
import com.comunicator.kkomunicatorbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    @Autowired
    UserService userService;

    @Autowired
    MessageService messageService;

    public User mapToUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setFriends(userService.getAllById(userDto.getFriendsId()));
        user.setFriendOf(userService.getAllById(userDto.getFriendsOfId()));
        user.setSentMessages(messageService.getAllById(userDto.getSentMessagesId()));
        user.setReceivedMessages(messageService.getAllById(userDto.getReceivedMessagesId()));
        return user;
    }

    public UserDto mapToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setFriendsId(user.getFriends().stream()
                .map(u -> u.getId())
                .collect(Collectors.toList())
        );
        userDto.setFriendsOfId(user.getFriendOf().stream()
                .map(u -> u.getId())
                .collect(Collectors.toList())
        );
        userDto.setSentMessagesId(user.getSentMessages().stream()
                .map(m -> m.getId())
                .collect(Collectors.toList())
        );
        userDto.setReceivedMessagesId(user.getReceivedMessages().stream()
                .map(m -> m.getId())
                .collect(Collectors.toList())
        );
        return userDto;
    }

    public List<UserDto> mapToUserDtoList(List<User> userList) {
        return userList.stream()
                .map(u -> new UserDto(
                        u.getId(),
                        u.getFirstName(),
                        u.getLastName(),
                        u.getEmail(),
                        u.getPassword(),
                        u.getFriends().stream().map(ud -> ud.getId()).collect(Collectors.toList()),
                        u.getFriendOf().stream().map(ud -> ud.getId()).collect(Collectors.toList()),
                        u.getSentMessages().stream().map(md -> md.getId()).collect(Collectors.toList()),
                        u.getReceivedMessages().stream().map(md -> md.getId()).collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
    }
}
