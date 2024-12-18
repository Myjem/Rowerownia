package com.rowerownia.rowerownia.DTO;

import com.rowerownia.rowerownia.entity.Enums;

import java.time.LocalDate;

public record UserDto(
        Integer userId,
        String login,
        String birthDate,
        String name,
        String surname
) { }
