package com.frederick.hotel.service;

import com.frederick.hotel.dto.RegistrationDto;
import com.frederick.hotel.dto.UserDto;
import com.frederick.hotel.models.User;

import java.util.List;

public interface UserService {

    List<UserDto> findAllUsers();
    User saveUser(UserDto userDto);
    UserDto findUserById(long userId);
    void updateUser(UserDto userDto);
    void delete(long userId);
    List<UserDto> searchUsers(String query);
    void createUser(RegistrationDto registrationDto);

    User findByEmail(String email);

    User findByUsername(String username);
}