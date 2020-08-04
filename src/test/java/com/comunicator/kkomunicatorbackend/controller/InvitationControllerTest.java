package com.comunicator.kkomunicatorbackend.controller;

import com.comunicator.kkomunicatorbackend.dto.InvitationDto;
import com.comunicator.kkomunicatorbackend.dto.UserDto;
import com.comunicator.kkomunicatorbackend.facade.InvitationFacade;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(InvitationController.class)
public class InvitationControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private InvitationFacade facade;

    @Test
    public void shouldFetchInvitationsList() throws Exception {
        //Given
        List<InvitationDto> invitationDtos = new ArrayList<>();
        invitationDtos.add(new InvitationDto(1L, 1L, 2L, LocalDate.now(), false));
        when(facade.getAll()).thenReturn(invitationDtos);
        //When & Then
        mockMvc.perform(get("/v1/invitation").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200)) //or isOk()
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].senderId", is(1)));
    }

    @Test
    public void shouldFetchInvitation() throws Exception {
        //Given
        InvitationDto invitationDto = new InvitationDto(1L, 1L, 2L, LocalDate.now(), false);
        when(facade.get(1L)).thenReturn(invitationDto);
        //When & Then
        mockMvc.perform(get("/v1/invitation/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.receiverId", is(2)));
    }

    @Test
    public void shouldFetchInvitationBySenderOrReceiver() throws Exception {
        //Given
        List<InvitationDto> invitationDtos = new ArrayList<>();
        invitationDtos.add(new InvitationDto(1L, 1L, 2L, LocalDate.now(), false));
        when(facade.getInvitationBySenderOrReceiverId(1L)).thenReturn(invitationDtos);
        //When & Then
        mockMvc.perform(get("/v1/invitation/getInvitationBySenderOrReceiverId/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].senderId", is(1)));
    }

    @Test
    public void shouldCreateInvitation()throws Exception{
        //Given
        InvitationDto invitationDto = new InvitationDto();
        when(facade.create(invitationDto)).thenReturn(invitationDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(invitationDto);
        //When & Then
        mockMvc.perform(post("/v1/invitation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldUpdateInvitation() throws Exception {
        //Given
        InvitationDto invitationDto = new InvitationDto();
        when(facade.update(invitationDto)).thenReturn(invitationDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(invitationDto);
        //When & Then
        mockMvc.perform(put("/v1/invitation")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteInvitation()throws Exception {
        //Given
        when(facade.delete(1L)).thenReturn(true);
        //When & Then
        mockMvc.perform(delete("/v1/invitation/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}