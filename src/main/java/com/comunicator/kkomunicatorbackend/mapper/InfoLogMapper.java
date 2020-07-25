package com.comunicator.kkomunicatorbackend.mapper;

import com.comunicator.kkomunicatorbackend.domain.InfoLog;
import com.comunicator.kkomunicatorbackend.dto.InfoLogDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InfoLogMapper {

    public InfoLog mapToInfoLog(final InfoLogDto infoLogDto) {
        return new InfoLog(
                infoLogDto.getId(),
                infoLogDto.getUser(),
                infoLogDto.getDate(),
                infoLogDto.getType()
        );
    }

    public InfoLogDto mapToInfoLogDto(final InfoLog infoLog) {
        return new InfoLogDto(
                infoLog.getId(),
                infoLog.getUser(),
                infoLog.getDate(),
                infoLog.getType()
        );
    }

    public List<InfoLog> mapToInfoLogList(final List<InfoLogDto> infoLogDtoList) {
        return infoLogDtoList.stream()
                .map(i -> new InfoLog(
                        i.getId(),
                        i.getUser(),
                        i.getDate(),
                        i.getType()
                ))
                .collect(Collectors.toList());
    }

    public List<InfoLogDto> mapToInfoLogDtoList(final List<InfoLog> infoLogList) {
        return infoLogList.stream()
                .map(i -> new InfoLogDto(
                        i.getId(),
                        i.getUser(),
                        i.getDate(),
                        i.getType()
                ))
                .collect(Collectors.toList());
    }
}
