package com.learning.security.services;

import com.learning.security.model.Role;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public interface RoleService {

    List<Role> findAll();

    List<GrantedAuthority> getGrantedAuthorities();
}
