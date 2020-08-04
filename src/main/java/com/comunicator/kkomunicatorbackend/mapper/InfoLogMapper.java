package com.comunicator.kkomunicatorbackend.mapper;

import com.comunicator.kkomunicatorbackend.controller.UserNotFoundException;
import com.comunicator.kkomunicatorbackend.domain.InfoLog;
import com.comunicator.kkomunicatorbackend.dto.InfoLogDto;
import com.comunicator.kkomunicatorbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InfoLogMapper {

    @Autowired
    UserService userService;

    public InfoLog mapToInfoLog(final InfoLogDto infoLogDto) throws UserNotFoundException {
        InfoLog infoLog = new InfoLog();
        infoLog.setId(infoLogDto.getId());
        infoLog.setUser(userService.getOne(infoLogDto.getUserId()));
        infoLog.setDate(infoLogDto.getDate());
        infoLog.setType(infoLogDto.getType());
        return infoLog;
    }

    public InfoLogDto mapToInfoLogDto(final InfoLog infoLog) {
        InfoLogDto infoLogDto = new InfoLogDto();
        infoLogDto.setId(infoLog.getId());
        infoLogDto.setUserId(infoLog.getUser().getId());
        infoLogDto.setDate(infoLog.getDate());
        infoLogDto.setType(infoLog.getType());
        return infoLogDto;
    }


    public List<InfoLogDto> mapToInfoLogDtoList(final List<InfoLog> infoLogList) {
        return infoLogList.stream()
                .map(i -> new InfoLogDto(
                        i.getId(),
                        i.getUser().getId(),
                        i.getDate(),
                        i.getType()
                ))
                .collect(Collectors.toList());
    }
}
