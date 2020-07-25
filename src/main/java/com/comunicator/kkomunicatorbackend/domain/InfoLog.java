package com.comunicator.kkomunicatorbackend.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity(name = "INFO_LOGS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InfoLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "LOG_ID")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "DATE")
    private LocalDate date;

    @Column(name = "TYPE")
    private String type;
}
