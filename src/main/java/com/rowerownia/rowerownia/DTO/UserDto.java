package com.rowerownia.rowerownia.DTO;

import com.rowerownia.rowerownia.entity.Enums;

import java.time.LocalDate;

public record UserDto(
        Integer userId,
        String login,
        String password,
        String birthDate,
        String name,
        String surname,
        String accessLevel,
        int failedLoginAttempts,
        boolean isBlocked

) { }
