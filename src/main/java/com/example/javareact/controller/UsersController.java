package com.example.javareact.controller;

import com.example.javareact.model.Users;
import com.example.javareact.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UsersController {

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("/users")
    @CrossOrigin(origins = {"http://localhost:3000"})
    public List<Users> findAllUsers(){
        return usersRepository.findAll();
    }
}
