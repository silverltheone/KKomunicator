package com.comunicator.kkomunicatorbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvitationDto {

    private Long id;
    private Long senderId;
    private Long receiverId;
    private LocalDate sendDate;
    private boolean wasRead;
}
