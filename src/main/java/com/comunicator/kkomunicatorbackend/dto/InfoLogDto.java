package com.comunicator.kkomunicatorbackend.dto;

import com.comunicator.kkomunicatorbackend.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class InfoLogDto {

    private Long id;
    private User user;
    private LocalDate date;
    private String type;
}
