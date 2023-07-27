package com.frederick.hotel.mapper;

import com.frederick.hotel.dto.UserDto;
import com.frederick.hotel.models.User;

import java.util.stream.Collectors;

public class UserMapper {

    public static User mapToUser(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .email(userDto.getEmail())
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .createdOn(userDto.getCreatedOn())
                .updatedOn(userDto.getUpdatedOn())
                .roles(userDto.getRoles().stream().map(RoleMapper::mapToRole).collect(Collectors.toList()))
                .bookings(userDto.getBookingDtoList().stream().map(BookingMapper::mapToBooking).collect(Collectors.toList()))
                .build();
    }

    public static UserDto mapToUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .password(user.getPassword())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .createdOn(user.getCreatedOn())
                .updatedOn(user.getUpdatedOn())
                .roles(user.getRoles().stream().map(RoleMapper::mapToRoleDto).collect(Collectors.toList()))
                .bookingDtoList(user.getBookings().stream().map(BookingMapper::mapToBookingDto).collect(Collectors.toList()))
                //.bookingDtoList(user.getBookings().stream().map(booking -> BookingMapper.mapToBookingDto(booking)).collect(Collectors.toList()))
                .build();
    }
}
