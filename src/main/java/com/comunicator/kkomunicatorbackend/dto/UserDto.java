package com.comunicator.kkomunicatorbackend.dto;

import com.comunicator.kkomunicatorbackend.domain.Message;
import com.comunicator.kkomunicatorbackend.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<User> friends;
    private List<User> friendOf;
    private List<Message> sentMessages;
    private List<Message> receivedMessages;
}
