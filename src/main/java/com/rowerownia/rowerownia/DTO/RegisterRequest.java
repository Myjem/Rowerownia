package com.rowerownia.rowerownia.DTO;

import com.rowerownia.rowerownia.entity.Enums;

public class RegisterRequest {
    private String login;
    private String password;
    private String name;
    private String surname;
    private String birthDate;
    private Enums.level level;

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLevel(Enums.level level) {
        this.level = level;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Enums.level getLevel() {
        return level;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public String getSurname(){
        return surname;
    }

    public String getBirthDate() {
        return birthDate;
    }

}
