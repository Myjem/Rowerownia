package com.rowerownia.rowerownia.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "user")
public class User {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence")
    private Integer userId;
    private String login;
    private String password;
    private LocalDate birthDate;
    private String name;
    private String surname;
    private enums.level accessLevel;

    public User() {
    }

    public User(String login, String password, LocalDate birthDate, String name, String surname, enums.level accessLevel) {
        this.login = login;
        this.password = password;
        this.birthDate = birthDate;
        this.name = name;
        this.surname = surname;
        this.accessLevel = accessLevel;
    }

    public Integer getUserId() {
        return userId;
    }

    public enums.level getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(enums.level accessLevel) {
        this.accessLevel = accessLevel;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", birthDate=" + birthDate +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", accessLevel='" + accessLevel + '\'' +
                '}';
    }
}
