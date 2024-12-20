package com.rowerownia.rowerownia.DTO;

import com.rowerownia.rowerownia.entity.Enums;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RegisterRequest {
    @NotNull(message = "Login is mandatory")
    @Size(min=2,max=12, message = "Login must be between 2 and 12 characters")
    private String login;
    @NotNull(message = "Password is mandatory")
    @Size(min=4, message = "Password must be at least 4 characters")
    private String password;
    @NotNull(message = "Name is mandatory")
    private String name;
    @NotNull(message = "Surname is mandatory")
    private String surname;
    @NotNull(message = "Birth date is mandatory")
    @Pattern(regexp = "^(19|20)\\d{2}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$", message = "Date must be in format yyyy-mm-dd")
    private String birthDate;
    @NotNull(message = "Level is mandatory")
    private Enums.level level= Enums.level.USER;

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
