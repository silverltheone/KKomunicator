package com.comunicator.kkomunicatorbackend.service;

import com.comunicator.kkomunicatorbackend.controller.MessageNotFoundException;
import com.comunicator.kkomunicatorbackend.controller.UserNotFoundException;
import com.comunicator.kkomunicatorbackend.domain.Message;
import com.comunicator.kkomunicatorbackend.domain.User;
import com.comunicator.kkomunicatorbackend.repository.MessageRepository;
import com.comunicator.kkomunicatorbackend.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserService service;

    @Mock
    private UserRepository repository;

    @Test
    public void testGelAllUsers() {
        //Given
        List<User> users = new ArrayList<>();
        User user = new User(1L,
                "testFirstName",
                "testLastName",
                "testEmail",
                "testPassword",
                new ArrayList<User>(),
                new ArrayList<User>(),
                new ArrayList<Message>(),
                new ArrayList<Message>());
        users.add(user);
        Mockito.when(repository.findAll()).thenReturn(users);
        //When
        List<User> testedList = service.getAll();
        //Then
        Assert.assertEquals(1, testedList.size());
        Assert.assertEquals("testFirstName", testedList.get(0).getFirstName());
    }

    @Test
    public void testGetUser() throws  UserNotFoundException {
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
        Mockito.when(repository.findById(1l)).thenReturn(java.util.Optional.of(user));
        //When
        User testedUser = service.getOne(1L);
        //Then
        Assert.assertNotNull(testedUser);
        Assert.assertEquals("testEmail", testedUser.getEmail());
    }

    @Test
    public void testUpdateUser() {
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

        Mockito.when(repository.findById(1L)).thenReturn(java.util.Optional.of(user));

        //When
        User updatedUser = service.update(user);
        //Then
        Assert.assertNotNull(updatedUser);
        Assert.assertEquals(updatedUser.getId(), user.getId());
        Mockito.verify(repository, Mockito.times(1)).save(updatedUser);
    }

    @Test
    public void testSaveUser() {
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

        Mockito.when(repository.save(user)).thenReturn(new User(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                user.getFriends(),
                user.getFriendOf(),
                user.getSentMessages(),
                user.getReceivedMessages()));
        //When
        User testedUser = service.create(user);
        //Then
        Mockito.verify(repository, Mockito.times(1)).save(user);
    }

    @Test
    public void testDeleteUser() {
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
        service.delete(1L);
        //Then
        Mockito.verify(repository, Mockito.times(1)).deleteById(1L);
    }
}