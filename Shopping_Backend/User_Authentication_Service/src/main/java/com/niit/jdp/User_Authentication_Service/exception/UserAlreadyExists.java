package com.niit.jdp.User_Authentication_Service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "UserName already exists")
public class UserAlreadyExists extends Exception {
}
