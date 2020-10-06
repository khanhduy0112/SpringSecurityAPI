package com.learning.security.services;

import com.learning.security.dto.UserDTO;
import com.learning.security.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    Optional<User> findById(Integer id);

    void save(User user);

    void deleteById(Integer id);

}
