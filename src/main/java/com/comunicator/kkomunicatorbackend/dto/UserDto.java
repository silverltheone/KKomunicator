package com.comunicator.kkomunicatorbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<Long> friendsId;
    private List<Long> friendsOfId;
    private List<Long> sentMessagesId;
    private List<Long> receivedMessagesId;

}
