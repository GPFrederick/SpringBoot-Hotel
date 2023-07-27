package com.frederick.hotel.mapper;

import com.frederick.hotel.dto.RoleDto;
import com.frederick.hotel.models.Role;
import com.frederick.hotel.models.User;

import java.util.stream.Collectors;

public class RoleMapper {

    public static Role mapToRole(RoleDto roleDto) {
        return Role.builder()
                .id(roleDto.getId())
                .name(roleDto.getName())
                .users(roleDto.getUsers())
                .build();
    }

    public static RoleDto mapToRoleDto(Role role) {
        return RoleDto.builder()
                .id(role.getId())
                .name(role.getName())
                .users(role.getUsers())
                .build();
    }
}
