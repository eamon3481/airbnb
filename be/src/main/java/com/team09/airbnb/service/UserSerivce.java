package com.team09.airbnb.service;

import com.team09.airbnb.domain.login.User;
import com.team09.airbnb.domain.login.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserSerivce {

    private UserRepository userRepository;

    public UserSerivce(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String signIn(User user) {
        signUp(user);
        return JWTBuilder.create(user);
    }

    private void signUp(User user) {
        userRepository.insert(user);
    }
}
