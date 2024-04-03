package com.krishnan.bookreview.controller;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krishnan.bookreview.model.User;
import com.krishnan.bookreview.services.UpdateRequest;
import com.krishnan.bookreview.services.UserService;

import io.micrometer.common.lang.NonNull;

@RestController
@RequestMapping ("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping ("/createUser")
    public ResponseEntity <User> add(@NonNull @RequestBody User user) {
        User createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("readUser/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        Optional<User> user = userService.getUserByEmail(email);
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/readUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PutMapping("updateUser/{email}")
    public ResponseEntity<User> updateUser(@NonNull @PathVariable  String email,@RequestBody User user)
    {
        if(userService.updateDetails(email,user)==true)
        {
            return new ResponseEntity<>(user,HttpStatus.OK);
        }
        
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("deleteUser/{userId}")
    public ResponseEntity<Void> removeUser(@NonNull @PathVariable Integer userId) {
        userService.removeUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

