package com.comunicator.kkomunicatorbackend.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity(name = "INVITATIONS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Invitation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "INVITATION_ID")
    private Long id;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "SENDER_USER_ID")
    private User sender;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "RECEIVER_USER_ID")
    private User receiver;

    @Column(name = "SEND_DATE")
    private LocalDate sendDate;

    @Column(name = "WAS_READ")
    private boolean wasRead;
}
