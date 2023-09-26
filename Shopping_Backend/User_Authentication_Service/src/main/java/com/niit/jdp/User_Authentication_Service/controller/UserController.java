package com.niit.jdp.User_Authentication_Service.controller;

import com.niit.jdp.User_Authentication_Service.domain.User;
import com.niit.jdp.User_Authentication_Service.exception.UserAlreadyExists;
import com.niit.jdp.User_Authentication_Service.exception.UserNotFound;
import com.niit.jdp.User_Authentication_Service.service.SecurityTokenGenerator;
import com.niit.jdp.User_Authentication_Service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final SecurityTokenGenerator securityTokenGenerator;


    @Autowired
    public UserController(UserService userService, SecurityTokenGenerator securityTokenGenerator) {
        this.userService = userService;
        this.securityTokenGenerator = securityTokenGenerator;
    }

    //    http://localhost:8088/user/add
    @PostMapping("/add")
    public ResponseEntity<?> addsUser(@RequestBody User user) throws UserAlreadyExists {
        try {
            return new ResponseEntity<>(this.userService.saveUser(user), HttpStatus.CREATED);

        } catch (UserAlreadyExists ec) {
            throw new UserAlreadyExists();
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    http://localhost:8088/user/login
    @PostMapping("/login")
    public ResponseEntity<?> logInsUser(@RequestBody User user) throws UserNotFound {
        try {
            this.userService.loginUser(user.getEmail(), user.getPassword());
            Map<String, String> key = this.securityTokenGenerator.generateToken(user);
            return new ResponseEntity<>(key, HttpStatus.OK);
        } catch (UserNotFound e) {
            throw new UserNotFound();
        } catch (Exception ex) {
            return new ResponseEntity<>("network error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //    http://localhost:8088/user/guard/delete/vamshi2@gmail
    @DeleteMapping("/guard/delete/{email}")
    public ResponseEntity<?> deletesUser(@PathVariable String email) {
        this.userService.deleteUserByEmail(email);
        return new ResponseEntity<>("user deleted successfully", HttpStatus.OK);
    }


    //    http://localhost:8088/user/guard/update/vamshi2@gmail
    @PutMapping("/guard/update/{email}")
    public ResponseEntity<?> updatesUser(@RequestBody User user, @PathVariable String email) {
        return new ResponseEntity<>(this.userService.updateUserByEmail(user, email), HttpStatus.OK);
    }


    //    http://localhost:8088/user/guard/all-emails
    @GetMapping("/guard/all-emails")
    public ResponseEntity<?> getsAllEmails() {
        return new ResponseEntity<>(this.userService.getAllEmails(), HttpStatus.OK);
    }


    //    http://localhost:8088/user/guard/email/vamshi2@gmail
    @GetMapping("/guard/email/{email}")
    public ResponseEntity<?> getsUserByEmail(@PathVariable String email) throws UserNotFound {
        return new ResponseEntity<>(this.userService.getUserByEmail(email), HttpStatus.OK);
    }

}
