package com.enigmacamp.restapi.repository;

import com.enigmacamp.restapi.model.User;

public interface IUserRepository {
    User[] getAll();
    User getOne(int id);
}
