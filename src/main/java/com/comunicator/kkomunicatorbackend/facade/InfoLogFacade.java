package com.comunicator.kkomunicatorbackend.facade;

import com.comunicator.kkomunicatorbackend.controller.InfoLogNotFoundException;
import com.comunicator.kkomunicatorbackend.controller.UserNotFoundException;
import com.comunicator.kkomunicatorbackend.domain.InfoLog;
import com.comunicator.kkomunicatorbackend.dto.InfoLogDto;
import com.comunicator.kkomunicatorbackend.mapper.InfoLogMapper;
import com.comunicator.kkomunicatorbackend.service.InfoLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InfoLogFacade {

    @Autowired
    private InfoLogService service;

    @Autowired
    private InfoLogMapper mapper;

    public List<InfoLogDto> getAll() {
        List<InfoLog> infoLogs = service.getAll();
        return mapper.mapToInfoLogDtoList(infoLogs);
    }

    public InfoLogDto get(Long id) throws InfoLogNotFoundException {
        InfoLog infoLog = service.getOne(id);
        return mapper.mapToInfoLogDto(infoLog);
    }

    public InfoLogDto create(InfoLogDto infoLogDto) throws UserNotFoundException {
        InfoLog infoLog = service.create(mapper.mapToInfoLog(infoLogDto));
        return mapper.mapToInfoLogDto(infoLog);
    }

    public InfoLogDto update(InfoLogDto infoLogDto) throws UserNotFoundException {
        InfoLog infoLog = service.update(mapper.mapToInfoLog(infoLogDto));
        return mapper.mapToInfoLogDto(infoLog);
    }

    public boolean delete(Long id) {
        return service.delete(id);
    }
}
