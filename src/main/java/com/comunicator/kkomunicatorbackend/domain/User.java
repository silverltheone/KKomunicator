package com.comunicator.kkomunicatorbackend.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "USERS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID")
    private Long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "EMAIL_ADRES")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @ManyToMany
    @JoinTable(name = "TBL_FRiENDS",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "friendId")
    )
    private List<User> friends;

    @ManyToMany
    @JoinTable(name = "TBL_FRIENDS",
            joinColumns = @JoinColumn(name = "friendId"),
            inverseJoinColumns = @JoinColumn(name = "userId")
    )
    private List<User> friendOf;

    @OneToMany(
            targetEntity = Message.class,
            mappedBy = "sender",
            fetch = FetchType.LAZY
    )
    private List<Message> sentMessages;

    @OneToMany(
            targetEntity = Message.class,
            mappedBy = "receiver",
            fetch = FetchType.LAZY
    )
    private List<Message> receivedMessages;
}
