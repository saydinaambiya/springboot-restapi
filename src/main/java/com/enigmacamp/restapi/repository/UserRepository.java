package com.enigmacamp.restapi.repository;

import com.enigmacamp.restapi.exception.NotFoundException;
import com.enigmacamp.restapi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Repository
public class UserRepository implements IUserRepository{

    @Value("${service.userUri}")
    private String serviceUserUri;

    private RestTemplate restTemplate;

    public UserRepository(@Autowired RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public User[] getAll() {
        try {
            ResponseEntity<User[]> response = restTemplate.getForEntity(serviceUserUri, User[].class);
            return response.getBody();
        } catch (RestClientException e) {
            throw new NotFoundException();
        }
    }

    @Override
    public User getOne(int id) {
        try {
            ResponseEntity<User> response = restTemplate.getForEntity(serviceUserUri+"/"+id, User.class);
            return response.getBody();
        } catch (RestClientException e) {
            throw new NotFoundException("User ID not register");
        }
    }
}
