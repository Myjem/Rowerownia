package com.rowerownia.rowerownia.dtomapper;

import com.rowerownia.rowerownia.DTO.UserDto;
import com.rowerownia.rowerownia.entity.User;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class UserDtoMapper implements Function<User, UserDto> {
    @Override
    public UserDto apply(User user) {
        return new UserDto(
                user.getUserId(),
                user.getLogin(),
                user.getPassword(),
                user.getBirthDate().toString(),
                user.getName(),
                user.getSurname(),
                user.getAccessLevel().toString(),
                user.getFailedLoginAttempts(),
                user.getIsBlocked()
        );
    }
}
