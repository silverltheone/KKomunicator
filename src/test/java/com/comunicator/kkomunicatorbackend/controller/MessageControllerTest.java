package com.comunicator.kkomunicatorbackend.controller;

import com.comunicator.kkomunicatorbackend.dto.InfoLogDto;
import com.comunicator.kkomunicatorbackend.dto.InvitationDto;
import com.comunicator.kkomunicatorbackend.dto.MessageDto;
import com.comunicator.kkomunicatorbackend.facade.InfoLogFacade;
import com.comunicator.kkomunicatorbackend.facade.MessageFacade;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MessageController.class)
public class MessageControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private MessageFacade facade;

    @Test
    public void shouldFetchMessageList() throws Exception {
        //Given
        List<MessageDto> messageDtos = new ArrayList<>();
        messageDtos.add(new MessageDto(1L, 1L, 2L, LocalDate.now(), "Testing", false));
        when(facade.getAll()).thenReturn(messageDtos);
        //When & Then
        mockMvc.perform(get("/v1/message").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200)) //or isOk()
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].message", is("Testing")));
    }

    @Test
    public void shouldFetchMessage() throws Exception {
        //Given
        MessageDto messageDto = new MessageDto(1L, 1L, 2L, LocalDate.now(), "Testing", false);
        when(facade.get(1L)).thenReturn(messageDto);
        //When & Then
        mockMvc.perform(get("/v1/message/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("Testing")));
    }

    @Test
    public void shouldFetchMessageBySenderOrReceiver() throws Exception {
        //Given
        List<MessageDto> messageDtos = new ArrayList<>();
        messageDtos.add(new MessageDto(1L, 1L, 2L, LocalDate.now(), "Testing", false));
        when(facade.getEmailBySenderOrReceiverId(1L)).thenReturn(messageDtos);
        //When & Then
        mockMvc.perform(get("/v1/message/getEmailBySenderOrReceiverId/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].senderId", is(1)));
    }

    @Test
    public void shouldCreateMessage()throws Exception{
        //Given
        MessageDto messageDto = new MessageDto();
        when(facade.create(messageDto)).thenReturn(messageDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(messageDto);
        //When & Then
        mockMvc.perform(post("/v1/message")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldUpdateMessage() throws Exception {
        //Given
        MessageDto messageDto = new MessageDto();
        when(facade.update(messageDto)).thenReturn(messageDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(messageDto);
        //When & Then
        mockMvc.perform(put("/v1/message")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteMessage()throws Exception {
        //Given
        when(facade.delete(1L)).thenReturn(true);
        //When & Then
        mockMvc.perform(delete("/v1/message/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}