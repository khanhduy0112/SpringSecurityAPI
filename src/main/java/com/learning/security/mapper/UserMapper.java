package com.learning.security.mapper;

import com.learning.security.dto.RoleDTO;
import com.learning.security.dto.UserDTO;
import com.learning.security.model.Role;
import com.learning.security.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {

    public static UserDTO toUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setPassword(user.getPassword());
        userDTO.setEmail(user.getEmail());
        List<RoleDTO> roleDTOS = new ArrayList<>();
        for (Role role : user.getRoles()) {
            roleDTOS.add(RoleMapper.toRoleDTO(role));
        }
        userDTO.setRoles(roleDTOS);
        return userDTO;
    }

    public static User toUser(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        List<Role> roles = new ArrayList<>();
        for (RoleDTO roleDTO : userDTO.getRoles()) {
            roles.add(RoleMapper.toRole(roleDTO));
        }
        user.setRoles(roles);
        return user;
    }
}
