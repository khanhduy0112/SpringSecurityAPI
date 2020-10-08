package com.learning.security.services;

import com.learning.security.dto.UserDTO;
import com.learning.security.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserDTO> findAll();

    UserDTO findById(Integer id);

    void save(UserDTO user);

    void deleteById(Integer id);

    List<UserDTO> findByNameContains(String name);


}
