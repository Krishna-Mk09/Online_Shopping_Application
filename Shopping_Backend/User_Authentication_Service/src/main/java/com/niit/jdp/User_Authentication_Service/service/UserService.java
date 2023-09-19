package com.niit.jdp.User_Authentication_Service.service;

import com.niit.jdp.User_Authentication_Service.domain.User;
import com.niit.jdp.User_Authentication_Service.exception.UserAlreadyExists;
import com.niit.jdp.User_Authentication_Service.exception.UserNotFound;

import java.util.List;

public interface UserService {
    User saveUser(User user) throws UserAlreadyExists;

    void deleteUserByEmail(String email);

    User loginUser(String email, String password) throws UserNotFound;

    User updateUserByEmail(User user, String email);

    User getUserByEmail(String email)throws UserNotFound;

    List<String> getAllEmails() ;
}
