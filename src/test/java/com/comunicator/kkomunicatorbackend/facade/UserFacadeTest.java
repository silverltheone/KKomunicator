package com.comunicator.kkomunicatorbackend.facade;

import com.comunicator.kkomunicatorbackend.domain.Message;
import com.comunicator.kkomunicatorbackend.domain.User;
import com.comunicator.kkomunicatorbackend.dto.UserDto;
import com.comunicator.kkomunicatorbackend.mapper.UserMapper;
import com.comunicator.kkomunicatorbackend.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class UserFacadeTest {

    @InjectMocks
    private UserFacade facade;

    @Mock
    private UserService service;

    @Mock
    private UserMapper mapper;

    @Test
    public void shouldFetchUserList() {
        //Given
        List<User> users = new ArrayList<>();
        users.add(new User(1L,
                "testFirstName",
                "testLastName",
                "testEmail",
                "testPassword",
                new ArrayList<User>(),
                new ArrayList<User>(),
                new ArrayList<Message>(),
                new ArrayList<Message>()));
        List<UserDto> userDtos = new ArrayList<>();
        userDtos.add(new UserDto(1L,
                "testFirstName",
                "testLastName",
                "testEmail",
                "testPassword",
                new ArrayList<Long>(),
                new ArrayList<Long>(),
                new ArrayList<Long>(),
                new ArrayList<Long>()));
        Mockito.when(service.getAll()).thenReturn(users);
        Mockito.when(mapper.mapToUserDtoList(users)).thenReturn(userDtos);
        //When
        List<UserDto> testedList = facade.getAll();
        //Then
        Assert.assertNotNull(testedList);
        Assert.assertEquals(1, testedList.size());
    }

    @Test
    public void shouldFetchUser() throws Exception {
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
        UserDto userDto = new UserDto(1L,
                "testFirstName",
                "testLastName",
                "testEmail",
                "testPassword",
                new ArrayList<Long>(),
                new ArrayList<Long>(),
                new ArrayList<Long>(),
                new ArrayList<Long>());

        Mockito.when(service.getOne(1L)).thenReturn(user);
        Mockito.when(mapper.mapToUserDto(user)).thenReturn(userDto);

        //When
        UserDto testedObject = facade.get(1L);
        //Then
        Assert.assertEquals("testFirstName", testedObject.getFirstName());
    }

    @Test
    public void shouldCreateUser()throws Exception {
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
        UserDto userDto = new UserDto(1L,
                "testFirstName",
                "testLastName",
                "testEmail",
                "testPassword",
                new ArrayList<Long>(),
                new ArrayList<Long>(),
                new ArrayList<Long>(),
                new ArrayList<Long>());

        Mockito.when(service.create(user)).thenReturn(user);
        Mockito.when(mapper.mapToUserDto(user)).thenReturn(userDto);
        Mockito.when(mapper.mapToUser(userDto)).thenReturn(user);

        //When
        UserDto testedObject = facade.create(userDto);
        //Then
        Assert.assertEquals("testLastName", testedObject.getLastName());
    }

    @Test
    public void shouldUpdateUser() throws Exception {
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
        UserDto userDto = new UserDto(1L,
                "testFirstName",
                "testLastName",
                "testEmail",
                "testPassword",
                new ArrayList<Long>(),
                new ArrayList<Long>(),
                new ArrayList<Long>(),
                new ArrayList<Long>());

        Mockito.when(service.update(user)).thenReturn(user);
        Mockito.when(mapper.mapToUserDto(user)).thenReturn(userDto);
        Mockito.when(mapper.mapToUser(userDto)).thenReturn(user);

        //When
        UserDto testedObject = facade.update(userDto);
        //Then
        Assert.assertEquals("testLastName", testedObject.getLastName());
    }

    @Test
    public void shouldDeleteUser()throws Exception {
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

        Mockito.when(service.delete(1L)).thenReturn(true);
        //When
        Boolean result = facade.delete(1L);
        //Then
        Assert.assertTrue(result);
    }
}