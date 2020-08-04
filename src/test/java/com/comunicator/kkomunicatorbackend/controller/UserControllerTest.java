package com.comunicator.kkomunicatorbackend.controller;

import com.comunicator.kkomunicatorbackend.dto.UserDto;
import com.comunicator.kkomunicatorbackend.facade.UserFacade;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private UserFacade facade;

    @Test
    public void shouldFetchUserList() throws Exception {
        //Given
        List<UserDto> userDtos = new ArrayList<>();
        userDtos.add(new UserDto(1L,
                "testFirstName",
                "testLastName",
                "testEmail",
                "testPassword",
                new ArrayList<Long>(),
                new ArrayList<Long>(),
                new ArrayList<Long>(),
                new ArrayList<Long>()
                ));
        when(facade.getAll()).thenReturn(userDtos);
        //When & Then
        mockMvc.perform(get("/v1/user").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200)) //or isOk()
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].email", is("testEmail")));
    }

    @Test
    public void shouldFetchUser() throws Exception {
        //Given
        UserDto userDto = new UserDto(1l,
                "testFirstName",
                "testLastName",
                "testEmail",
                "testPassword",
                new ArrayList<Long>(),
                new ArrayList<Long>(),
                new ArrayList<Long>(),
                new ArrayList<Long>()
        );
        when(facade.get(1L)).thenReturn(userDto);
        //When & Then
        mockMvc.perform(get("/v1/user/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", is("testEmail")))
                .andExpect(jsonPath("$.password", is("testPassword")));
    }

    @Test
    public void shouldFetchUserByEmailAndPassword() throws Exception {
        //Given
        UserDto userDto = new UserDto(1l,
                "testFirstName",
                "testLastName",
                "testEmail",
                "testPassword",
                new ArrayList<Long>(),
                new ArrayList<Long>(),
                new ArrayList<Long>(),
                new ArrayList<Long>()
        );
        when(facade.getByEmailAndPassword("testEmail", "testPassword")).thenReturn(userDto);
        //When & Then
        mockMvc.perform(get("/v1/user/getByEmailAndPassword/testEmail/testPassword")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("testFirstName")))
                .andExpect(jsonPath("$.lastName", is("testLastName")));
    }

    @Test
    public void shouldFetchUserByEmail() throws Exception {
        //Given
        UserDto userDto = new UserDto(1l,
                "testFirstName",
                "testLastName",
                "testEmail",
                "testPassword",
                new ArrayList<Long>(),
                new ArrayList<Long>(),
                new ArrayList<Long>(),
                new ArrayList<Long>()
        );
        when(facade.getByEmail("testEmail")).thenReturn(userDto);
        //When & Then
        mockMvc.perform(get("/v1/user/getByEmail/testEmail")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("testFirstName")))
                .andExpect(jsonPath("$.lastName", is("testLastName")));
    }

    @Test
    public void shouldCreateUser()throws Exception{
        //Given
        UserDto userDto = new UserDto(1l,
                "testFirstName",
                "testLastName",
                "testEmail",
                "testPassword",
                new ArrayList<Long>(),
                new ArrayList<Long>(),
                new ArrayList<Long>(),
                new ArrayList<Long>()
        );
        when(facade.create(userDto)).thenReturn(userDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(userDto);
        //When & Then
        mockMvc.perform(post("/v1/user")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldUpdateUser()throws Exception {
        //Given
        UserDto userDto = new UserDto(1l,
                "testFirstName",
                "testLastName",
                "testEmail",
                "testPassword",
                new ArrayList<Long>(),
                new ArrayList<Long>(),
                new ArrayList<Long>(),
                new ArrayList<Long>()
        );
        when(facade.update(userDto)).thenReturn(userDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(userDto);
        //When & Then
        mockMvc.perform(put("/v1/user")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteUser()throws Exception {
        //Given
        when(facade.delete(1L)).thenReturn(true);
        //When & Then
        mockMvc.perform(delete("/v1/user/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}