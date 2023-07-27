package com.frederick.hotel.service.impl;

import com.frederick.hotel.dto.RoleDto;
import com.frederick.hotel.mapper.RoleMapper;
import com.frederick.hotel.models.Role;
import com.frederick.hotel.models.User;
import com.frederick.hotel.repository.RoleRepository;
import com.frederick.hotel.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<RoleDto> findAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream().map(RoleMapper::mapToRoleDto).collect(Collectors.toList());
    }

    @Override
    public RoleDto findRoleById(long roleId) {
        Role role = roleRepository.findById(roleId).get();
        return RoleMapper.mapToRoleDto(role);
    }

    public List<Role> getUserRole(User user) {
        return user.getRoles();
    }
}
