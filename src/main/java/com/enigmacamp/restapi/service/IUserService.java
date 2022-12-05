package com.enigmacamp.restapi.service;

import com.enigmacamp.restapi.model.User;

public interface IUserService {
    User[] findAll();
    User getById(int id);
}
