package com.enigmacamp.restapi.controller;

import com.enigmacamp.restapi.model.User;
import com.enigmacamp.restapi.model.response.SuccessResponse;
import com.enigmacamp.restapi.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private IUserService userService;

    public UserController(@Autowired IUserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity getALlUsers(){
        User[] users = userService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success get all users", users));
    }

    @GetMapping("/{id}")
    public ResponseEntity getUserById(@PathVariable("id") int id){
        User user = userService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success get user by ID", user));
    }
}
