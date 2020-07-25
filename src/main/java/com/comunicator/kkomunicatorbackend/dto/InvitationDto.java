package com.comunicator.kkomunicatorbackend.dto;

import com.comunicator.kkomunicatorbackend.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;


@Getter
@AllArgsConstructor
public class InvitationDto {

    private Long id;
    private User sender;
    private User receiver;
    private LocalDate sendDate;
}
