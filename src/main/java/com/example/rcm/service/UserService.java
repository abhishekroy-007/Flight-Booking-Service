package com.example.rcm.service;

import com.example.rcm.entity.User;
import com.example.rcm.model.UserRequest;
import com.example.rcm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    // Idempotent API On Phone Number
    public User createUser(UserRequest userRequest) {
        User existingUser = getUserByPhoneNumber(userRequest.getPhoneNumber());
        if(existingUser == null){
            User user = User.builder()
                    .email(userRequest.getEmail())
                    .name(userRequest.getName())
                    .phoneNumber(userRequest.getPhoneNumber())
                    .build();
            user = userRepository.save(user);
            return user;
        }
        return existingUser;
    }

    public User getUserByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber);
    }
}
