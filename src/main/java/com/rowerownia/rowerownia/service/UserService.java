package com.rowerownia.rowerownia.service;

import com.rowerownia.rowerownia.DTO.UserDto;
import com.rowerownia.rowerownia.dtomapper.UserDtoMapper;
import com.rowerownia.rowerownia.entity.User;
import com.rowerownia.rowerownia.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserDtoMapper userDtoMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserDtoMapper userDtoMapper) {
        this.userRepository = userRepository;
        this.userDtoMapper = userDtoMapper;
    }

    public List<UserDto> getUsers() {
        return userRepository.findAll().
                stream().
                map(userDtoMapper).collect(Collectors.toList());
    }

    public  void addNewUser(User user) {
        Optional<User> userByLogin = userRepository.findUserByLogin(user.getLogin());
        if(userByLogin.isPresent()){
            throw new IllegalStateException("login taken");
        }
        userRepository.save(user);
    }

    public void deleteUser(Integer userId){
        boolean exists = userRepository.existsById(userId);
        if(!exists){
            throw new IllegalStateException("user with id " + userId + " does not exists");
        }
        userRepository.deleteById(userId);
    }

    @Transactional
    public void updateUser(Integer userId, String name, String surname){
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("user with id " + userId + " does not exists"));

        if (name != null &&
                name.length() > 0 &&
                !Objects.equals(user.getName(),name)){
            user.setName(name);
        }
        if (surname != null &&
                surname.length() > 0 &&
                !Objects.equals(user.getSurname(),surname)){
            user.setSurname(surname);
        }
    }


}
