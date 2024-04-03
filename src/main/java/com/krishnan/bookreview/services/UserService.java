package com.krishnan.bookreview.services;

import java.util.Optional;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.krishnan.bookreview.model.User;
import com.krishnan.bookreview.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    //new user
    public User createUser(@NonNull User user)
    {
        return userRepository.save(user);
    }
    //getuser
    public Optional <User>getUserByEmail(String email)
    {
        return userRepository.findByEmail(email);
    }
    //getUsers
    public List<User> getAllUsers()
    {
        return userRepository.findAll();
    }

    //updateUser
    // public User updateUser(@NonNull String email,UpdateRequest updateRequest)
    // {
    //     return userRepository.findByEmail(email)
    //             .map(existingUser->
    //             {
    //                 existingUser.setName(updateRequest.getName());
    //                 existingUser.setEmail(updateRequest.getEmail());
    //                 existingUser.setMobile(updateRequest.getMobile());
    //                 existingUser.setPassword(updateRequest.getPassword());
    //                 return userRepository.save(existingUser);  
    //           }).orElseThrow(()->new RuntimeException("user not found for this email: "+email));
    // }

    public boolean updateDetails(String email,User user)
    {
        if(this.getUserByEmail(email)==null)
        {
            return false;
        }
        try{
            userRepository.save(user);
        }
        catch(Exception e)
        {
            return false;
        }
        return true;
    }

    //deleteUser
    public void removeUser(@NonNull Integer userId)
    {
        userRepository.deleteById(userId);
    }
}
