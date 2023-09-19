package com.niit.jdp.User_Authentication_Service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND,reason = "UserName not found")
public class UserNotFound extends Exception {
}
