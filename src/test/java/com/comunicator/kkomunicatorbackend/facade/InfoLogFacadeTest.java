package com.comunicator.kkomunicatorbackend.facade;

import com.comunicator.kkomunicatorbackend.domain.InfoLog;
import com.comunicator.kkomunicatorbackend.domain.User;
import com.comunicator.kkomunicatorbackend.dto.InfoLogDto;
import com.comunicator.kkomunicatorbackend.mapper.InfoLogMapper;
import com.comunicator.kkomunicatorbackend.service.InfoLogService;
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
public class InfoLogFacadeTest {

    @InjectMocks
    private InfoLogFacade facade;

    @Mock
    private InfoLogService service;

    @Mock
    private InfoLogMapper mapper;

    @Test
    public void shouldFetchInfoLogList()  {
        //Given
        List<InfoLog> infoLogs = new ArrayList<>();
        infoLogs.add(new InfoLog(1L, new User(), LocalDate.now(), "TESTING"));
        List<InfoLogDto> infoLogDtos = new ArrayList<>();
        infoLogDtos.add(new InfoLogDto(1L, 1L, LocalDate.now(), "TESTING"));

        Mockito.when(service.getAll()).thenReturn(infoLogs);
        Mockito.when(mapper.mapToInfoLogDtoList(infoLogs)).thenReturn(infoLogDtos);
        //When
        List<InfoLogDto> testedList = facade.getAll();
        //Then
        Assert.assertNotNull(testedList);
        Assert.assertEquals(1, testedList.size());
    }

    @Test
    public void shouldFetchInfoLog() throws Exception {
        //Given
        InfoLog infoLog = new InfoLog(1L, new User(), LocalDate.now(), "TESTING");
        InfoLogDto infoLogDto = new InfoLogDto(1L, 1L, LocalDate.now(), "TESTING");

        Mockito.when(service.getOne(1L)).thenReturn(infoLog);
        Mockito.when(mapper.mapToInfoLogDto(infoLog)).thenReturn(infoLogDto);

        //When
        InfoLogDto testedObject = facade.get(1L);
        //Then
        Assert.assertEquals("TESTING", testedObject.getType());
    }

    @Test
    public void shouldCreateInfoLog()throws Exception{
        //Given
        InfoLog infoLog = new InfoLog(1L, new User(), LocalDate.now(), "TESTING");
        InfoLogDto infoLogDto = new InfoLogDto(1L, 1L, LocalDate.now(), "TESTING");

        Mockito.when(service.create(infoLog)).thenReturn(infoLog);
        Mockito.when(mapper.mapToInfoLogDto(infoLog)).thenReturn(infoLogDto);
        Mockito.when(mapper.mapToInfoLog(infoLogDto)).thenReturn(infoLog);

        //When
        InfoLogDto testedObject = facade.create(infoLogDto);
        //Then
        Assert.assertEquals("TESTING", testedObject.getType());
    }

    @Test
    public void shouldUpdateInfoLog() throws Exception {
        //Given
        InfoLog infoLog = new InfoLog(1L, new User(), LocalDate.now(), "TESTING");
        InfoLogDto infoLogDto = new InfoLogDto(1L, 1L, LocalDate.now(), "TESTING");

        Mockito.when(service.update(infoLog)).thenReturn(infoLog);
        Mockito.when(mapper.mapToInfoLogDto(infoLog)).thenReturn(infoLogDto);
        Mockito.when(mapper.mapToInfoLog(infoLogDto)).thenReturn(infoLog);

        //When
        InfoLogDto testedObject = facade.update(infoLogDto);
        //Then
        Assert.assertEquals("TESTING", testedObject.getType());
    }

    @Test
    public void shouldDeleteInfoLog()throws Exception {
        //Given
        InfoLog infoLog = new InfoLog(1L, new User(), LocalDate.now(), "TESTING");

        Mockito.when(service.delete(1L)).thenReturn(true);
        //When
        Boolean result = facade.delete(1L);
        //Then
        Assert.assertTrue(result);
    }
}