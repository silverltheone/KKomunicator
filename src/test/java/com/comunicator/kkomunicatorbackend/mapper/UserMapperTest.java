package com.comunicator.kkomunicatorbackend.mapper;

import com.comunicator.kkomunicatorbackend.domain.Message;
import com.comunicator.kkomunicatorbackend.domain.User;
import com.comunicator.kkomunicatorbackend.dto.UserDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class UserMapperTest {

    @InjectMocks
    private UserMapper mapper;

    @Test
    public void testMapToUserDto() {
        //Given
        User user = new User(1L,
                "testFirstName",
                "testLastName",
                "testEmail",
                "testPassword",
                new ArrayList<User>(),
                new ArrayList<User>(),
                new ArrayList<Message>(),
                new ArrayList<Message>());
        //When
        UserDto userDto = mapper.mapToUserDto(user);
        //Then
        Assert.assertEquals("testFirstName", userDto.getFirstName());
        Assert.assertEquals("testPassword", userDto.getPassword());
    }

    @Test
    public void testMapToUserDtoList() {
        //Given
        User user1 = new User(1L,
                "testFirstName",
                "testLastName",
                "testEmail",
                "testPassword",
                new ArrayList<User>(),
                new ArrayList<User>(),
                new ArrayList<Message>(),
                new ArrayList<Message>());

        User user2 = new User(2L,
                "testFirstName2",
                "testLastName2",
                "testEmail2",
                "testPassword2",
                new ArrayList<User>(),
                new ArrayList<User>(),
                new ArrayList<Message>(),
                new ArrayList<Message>());

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        //When
        List<UserDto> userDtos = mapper.mapToUserDtoList(users);
        //Then
        Assert.assertEquals(2, userDtos.size());
        Assert.assertEquals("testLastName2", userDtos.get(1).getLastName());
    }
}
