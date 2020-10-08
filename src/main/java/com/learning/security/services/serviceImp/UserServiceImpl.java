package com.learning.security.services.serviceImp;

import com.learning.security.dto.UserDTO;
import com.learning.security.mapper.UserMapper;
import com.learning.security.model.User;
import com.learning.security.repository.UserRepository;
import com.learning.security.services.UserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackOn = Exception.class)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User user : users) {
            userDTOS.add(UserMapper.toUserDTO(user));
        }
        return userDTOS;
    }

    @Override
    public UserDTO findById(Integer id) {
        Optional<User> userOptional = userRepository.findById(id);
        return UserMapper.toUserDTO(userOptional.get());
    }

    @Override
    public void save(UserDTO userDTO) {

    }

    @Override
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<UserDTO> findByNameContains(String name) {
        List<User> users = userRepository.findByNameContains(name);
        List<UserDTO> result = new ArrayList<>();
        for (User user : users) {
            result.add(UserMapper.toUserDTO(user));
        }
        return result;
    }


}
