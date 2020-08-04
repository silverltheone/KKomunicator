package com.comunicator.kkomunicatorbackend.service;

import com.comunicator.kkomunicatorbackend.controller.InfoLogNotFoundException;
import com.comunicator.kkomunicatorbackend.domain.InfoLog;
import com.comunicator.kkomunicatorbackend.domain.User;
import com.comunicator.kkomunicatorbackend.repository.InfoLogRepository;
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
public class InfoLogServiceTest {

    @InjectMocks
    private InfoLogService service;

    @Mock
    private InfoLogRepository repository;

    @Test
    public void testGelAllInfologs() {
        //Given
        List<InfoLog> infoLogs = new ArrayList<>();
        infoLogs.add(new InfoLog(1L, new User(), LocalDate.now(), "TESTING"));
        Mockito.when(repository.findAll()).thenReturn(infoLogs);
        //When
        List<InfoLog> testedList = service.getAll();
        //Then
        Assert.assertEquals(1, testedList.size());
        Assert.assertEquals("TESTING", testedList.get(0).getType());
    }

    @Test
    public void testGetInfolog() throws InfoLogNotFoundException {
        //Given
        InfoLog infoLog = new InfoLog(1L, new User(), LocalDate.now(), "TESTING");
        Mockito.when(repository.findById(1l)).thenReturn(java.util.Optional.of(infoLog));
        //When
        InfoLog testedInfoLog = service.getOne(1L);
        //Then
        Assert.assertNotNull(testedInfoLog);
        Assert.assertEquals("TESTING", testedInfoLog.getType());
    }

    @Test
    public void testUpdateInfolog() {
        //Given
        InfoLog infoLog = new InfoLog(1L, new User(), LocalDate.now(), "TESTING");

        Mockito.when(repository.findById(1L)).thenReturn(java.util.Optional.of(infoLog));

        //When
        InfoLog updatedInfoLog = service.update(infoLog);
        //Then
        Assert.assertNotNull(updatedInfoLog);
        Assert.assertEquals(updatedInfoLog.getId(), infoLog.getId());
        Mockito.verify(repository, Mockito.times(1)).save(updatedInfoLog);
    }

    @Test
    public void testSaveInfolog() {
        //Given
        InfoLog infoLog = new InfoLog(1L, new User(), LocalDate.now(), "TESTING");

        Mockito.when(repository.save(infoLog)).thenReturn(new InfoLog(infoLog.getId(), infoLog.getUser(), infoLog.getDate(), infoLog.getType()));
        //When
        InfoLog testedInfoLog = service.create(infoLog);
        //Then
        Mockito.verify(repository, Mockito.times(1)).save(infoLog);
    }

    @Test
    public void testDeleteInfolog() {
        //Given
        InfoLog infoLog = new InfoLog(1L, new User(), LocalDate.now(), "TESTING");

        //When
        service.delete(1L);
        //Then
        Mockito.verify(repository, Mockito.times(1)).deleteById(1L);
    }
}