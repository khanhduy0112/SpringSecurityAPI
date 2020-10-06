package com.learning.security.services.serviceImp;

import com.learning.security.model.Role;
import com.learning.security.repository.RoleRepository;
import com.learning.security.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class RoleServiceImp implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImp(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public List<GrantedAuthority> getGrantedAuthorities() {
        return roleRepository.findAll().stream()
                .map(role -> new SimpleGrantedAuthority(role.getType()))
                .collect(Collectors.toList());
    }

}
