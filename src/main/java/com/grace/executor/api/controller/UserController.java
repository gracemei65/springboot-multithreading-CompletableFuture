package com.grace.executor.api.controller;

import com.grace.executor.api.entity.User;
import com.grace.executor.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to com.grace !!";
    }
    
    @PostMapping(value = "/users", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = "application/json")
    public ResponseEntity saveUsers(@RequestParam(value = "users") MultipartFile[] files) throws Exception {
        for (MultipartFile file : files) {
            service.saveUsers(file);
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

//    @GetMapping(value = "/users", produces = "application/json")
//    public CompletableFuture<ResponseEntity> findAllUsers() {
//       return  service.findAllUsers().thenApply(ResponseEntity::ok);
//    }


    @GetMapping(value = "/users", produces = "application/json")
    public  CompletableFuture<List<User>> getUsers(){

        CompletableFuture<List<User>> users1=service.findAllUsers();
        CompletableFuture<List<User>> users2=service.findAllUsers();
        CompletableFuture<List<User>> users3=service.findAllUsers();
        CompletableFuture.allOf(users1,users2,users3).join();

        return  users3;
    }
}
