package com.teachmeskills.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.NonFinal;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@NoArgsConstructor
@NonFinal
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name = "login")
    String login;

    @Column(name = "password")
    String password;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "requestFrom")
    private List<FriendRequest> outcomingRequests;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "requestTo")
    private List<FriendRequest> incomingRequests;
}
