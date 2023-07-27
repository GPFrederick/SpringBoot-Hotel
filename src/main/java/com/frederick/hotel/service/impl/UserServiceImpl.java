package com.frederick.hotel.service.impl;

import com.frederick.hotel.dto.RegistrationDto;
import com.frederick.hotel.dto.UserDto;
import com.frederick.hotel.mapper.UserMapper;
import com.frederick.hotel.models.Role;
import com.frederick.hotel.models.User;
import com.frederick.hotel.repository.RoleRepository;
import com.frederick.hotel.repository.UserRepository;
import com.frederick.hotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> UserMapper.mapToUserDto(user)).collect(Collectors.toList());
    }

    //This one needs to be replaced. This is not to create user.
    @Override
    public User saveUser(UserDto userDto) {
        User user = UserMapper.mapToUser(userDto);
        return userRepository.save(user);
    }

    @Override
    public UserDto findUserById(long userId) {
        User user = userRepository.findById(userId).get();
        return UserMapper.mapToUserDto(user);
    }

    @Override
    public void updateUser(UserDto userDto) {
        User user = UserMapper.mapToUser(userDto);
        userRepository.save(user);
    }

    @Override
    public void delete(long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public List<UserDto> searchUsers(String query) {
        List<User> users = userRepository.searchUsers(query);
        return users.stream().map(user -> UserMapper.mapToUserDto(user)).collect(Collectors.toList());
    }

    @Override
    public void createUser(RegistrationDto registrationDto) {
        User user = new User();
        user.setUsername(registrationDto.getUsername());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        user.setEmail(registrationDto.getEmail());
        user.setFirstName(registrationDto.getFirstName());
        user.setLastName(registrationDto.getLastName());
        Role role = roleRepository.findByName("USER");
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
