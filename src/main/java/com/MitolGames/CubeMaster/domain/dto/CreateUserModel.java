package com.MitolGames.CubeMaster.domain.dto;

import lombok.Getter;
import lombok.Setter;


public class CreateUserModel {

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

    private String userName;

    private String password;
}
