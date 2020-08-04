package com.comunicator.kkomunicatorbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {

    private Long id;
    private Long senderId;
    private Long receiverId;
    private LocalDate sendDate;
    private String message;
    private boolean wasRead;
}
