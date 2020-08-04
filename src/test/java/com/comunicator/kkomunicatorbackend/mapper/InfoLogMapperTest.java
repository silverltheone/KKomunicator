package com.comunicator.kkomunicatorbackend.mapper;

import com.comunicator.kkomunicatorbackend.domain.InfoLog;
import com.comunicator.kkomunicatorbackend.domain.User;
import com.comunicator.kkomunicatorbackend.dto.InfoLogDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class InfoLogMapperTest {

    @InjectMocks
    private InfoLogMapper mapper;

    @Test
    public void testMapToInfoLogDto() {
        //Given
        InfoLog infoLog = new InfoLog(1L, new User(), LocalDate.now(), "TESTING");
        //When
        InfoLogDto infoLogDto = mapper.mapToInfoLogDto(infoLog);
        //Then
        Assert.assertEquals("TESTING", infoLogDto.getType());
    }

    @Test
    public void testMapToInfoLogDtosList() {
        //Given
        InfoLog infoLog1 = new InfoLog(1L, new User(), LocalDate.now(), "TESTING");

        InfoLog infoLog2 = new InfoLog(2L, new User(), LocalDate.now(), "TESTING2");

        List<InfoLog> infoLogs = new ArrayList<>();
        infoLogs.add(infoLog1);
        infoLogs.add(infoLog2);
        //When
        List<InfoLogDto> infoLogDtos = mapper.mapToInfoLogDtoList(infoLogs);
        //Then
        Assert.assertEquals(2, infoLogDtos.size());
        Assert.assertEquals("TESTING2", infoLogDtos.get(1).getType());
    }
}