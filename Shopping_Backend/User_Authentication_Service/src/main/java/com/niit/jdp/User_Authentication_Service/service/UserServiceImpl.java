package com.niit.jdp.User_Authentication_Service.service;

import com.niit.jdp.User_Authentication_Service.domain.User;
import com.niit.jdp.User_Authentication_Service.exception.UserAlreadyExists;
import com.niit.jdp.User_Authentication_Service.exception.UserNotFound;
import com.niit.jdp.User_Authentication_Service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) throws UserAlreadyExists {
        if (userRepository.findById(user.getEmail()).isPresent()) {
            throw new UserAlreadyExists();
        }
        return this.userRepository.save(user);
    }

    @Override
    public User loginUser(String email, String password) throws UserNotFound {
        User userByEmailAndPassword = this.userRepository.findUserByEmailAndPassword(email, password);
        if (userByEmailAndPassword == null) {
            throw new UserNotFound();
        }
        return userByEmailAndPassword;
    }
}
