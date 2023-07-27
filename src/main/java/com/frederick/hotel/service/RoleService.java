package com.frederick.hotel.service;

import com.frederick.hotel.dto.RoleDto;

import java.util.List;

public interface RoleService {

    List<RoleDto> findAllRoles();
    RoleDto findRoleById(long roleId);
}
