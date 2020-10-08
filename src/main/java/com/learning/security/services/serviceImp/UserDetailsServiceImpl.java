package com.learning.security.services.serviceImp;

import com.learning.security.model.User;
import com.learning.security.repository.UserRepository;
import com.learning.security.security.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByName(username);
        optionalUser.orElseThrow(() -> new UsernameNotFoundException(String.format("Not found username : %s", username)));
        User user = optionalUser.get();
        return new CustomUserDetails(user);
    }
}
