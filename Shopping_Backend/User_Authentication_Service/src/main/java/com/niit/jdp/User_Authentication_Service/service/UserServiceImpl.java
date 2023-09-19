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
    public void deleteUserByEmail(String email) {
        this.userRepository.deleteById(email);
    }

    @Override
    public User loginUser(String email, String password) throws UserNotFound {
        User userByEmailAndPassword = this.userRepository.findUserByEmailAndPassword(email, password);
        if (userByEmailAndPassword == null) {
            throw new UserNotFound();
        }
        return userByEmailAndPassword;
    }

    @Override
    public User updateUserByEmail(User user, String email) {
        return this.userRepository.save(user);
    }

    @Override
    public User getUserByEmail(String email) throws UserNotFound {
        User userByEmail = this.userRepository.findUserByEmail(email);
        if (userByEmail == null) {
            throw new UserNotFound();
        }
        return userByEmail;
    }

    @Override
    public List<String> getAllEmails() {
        List<User> users = userRepository.findAll();
        List<String> emails = new ArrayList<>();
        for (User user : users) {
            emails.add(user.getEmail());
        }
        return emails;
    }
}
