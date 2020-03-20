package com.MitolGames.CubeMaster.domain;


import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;


@Entity
@Table(name = "user_table")
public class ApplictaionUser {
    @JsonView(Views.PublicUserInfo.class)
    @Column(name = "user_name")
    String userName;

    @Column(name = "password")
    String password;
    @JsonView(Views.PublicUserInfo.class)
    @Column(name = "id", updatable = false, nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usr_sequence")
    Long id;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}