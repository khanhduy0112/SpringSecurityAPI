package com.learning.security.mapper;

import com.learning.security.dto.RoleDTO;
import com.learning.security.model.Role;
import lombok.Data;

@Data
public class RoleMapper {

    public static RoleDTO toRoleDTO(Role role) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(role.getId());
        roleDTO.setType(role.getType());
        roleDTO.setName(role.getName());
        return roleDTO;
    }

    public static Role toRole(RoleDTO roleDTO) {
        Role role = new Role();
        role.setId(roleDTO.getId());
        role.setName(roleDTO.getName());
        role.setType(roleDTO.getType());
        return role;
    }


}
