package com.comunicator.kkomunicatorbackend.controller;

import com.comunicator.kkomunicatorbackend.dto.InfoLogDto;
import com.comunicator.kkomunicatorbackend.facade.InfoLogFacade;
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
@WebMvcTest(InfoLogController.class)
public class InfoLogControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private InfoLogFacade facade;

    @Test
    public void shouldFetchInfoLogList() throws Exception {
        //Given
        List<InfoLogDto> infoLogDtos = new ArrayList<>();
        infoLogDtos.add(new InfoLogDto(1L, 1L, LocalDate.now(), "TESTING"));
        when(facade.getAll()).thenReturn(infoLogDtos);
        //When & Then
        mockMvc.perform(get("/v1/infoLogs").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200)) //or isOk()
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].type", is("TESTING")));
    }

    @Test
    public void shouldFetchInfoLog() throws Exception {
        //Given
        InfoLogDto infoLogDto = new InfoLogDto(1L, 1L, LocalDate.now(), "TESTING");
        when(facade.get(1L)).thenReturn(infoLogDto);
        //When & Then
        mockMvc.perform(get("/v1/infoLogs/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type", is("TESTING")));
    }

    @Test
    public void shouldCreateInfoLog()throws Exception{
        //Given
        InfoLogDto infoLogDto = new InfoLogDto();
        when(facade.create(infoLogDto)).thenReturn(infoLogDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(infoLogDto);
        //When & Then
        mockMvc.perform(post("/v1/infoLogs")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldUpdateInfoLog() throws Exception {
        //Given
        InfoLogDto infoLogDto = new InfoLogDto();
        when(facade.update(infoLogDto)).thenReturn(infoLogDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(infoLogDto);
        //When & Then
        mockMvc.perform(put("/v1/infoLogs")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteInfoLog()throws Exception {
        //Given
        when(facade.delete(1L)).thenReturn(true);
        //When & Then
        mockMvc.perform(delete("/v1/infoLogs/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}