package com.learning.security.services.serviceImp;

import com.learning.security.dto.UserDTO;
import com.learning.security.model.User;
import com.learning.security.repository.UserRepository;
import com.learning.security.services.UserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public void save(User user) {
        if (user != null) {
            userRepository.save(user);
        }
    }

    @Override
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }


}
