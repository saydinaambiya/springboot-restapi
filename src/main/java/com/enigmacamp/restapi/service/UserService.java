package com.enigmacamp.restapi.service;

import com.enigmacamp.restapi.model.User;
import com.enigmacamp.restapi.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService{
    private IUserRepository userRepository;

    public UserService(@Autowired IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User[] findAll() {
        return userRepository.getAll();
    }

    @Override
    public User getById(int id) {
        return userRepository.getOne(id);
    }
}
