package com.enigmacamp.restapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class Hello {

    @GetMapping("/{name}")
    public String sayHelloName(@PathVariable("name") String name) {
        return "Hello! " + name;
    }

    @GetMapping
    public String sayHello(){
        return "Hello!";
    }
}
