package com.example.rcm.controller;

import com.example.rcm.entity.User;
import com.example.rcm.model.UserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface IUserController {

    @PostMapping(path = "/v1/createUser")
    ResponseEntity<User> createUser(@RequestBody UserRequest userRequest);

    @GetMapping(path = "/v1/getUserByPhoneNumber")
    ResponseEntity<User> getUserByPhoneNumber(@RequestParam String phoneNumber);
}
