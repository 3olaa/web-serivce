package com.SW.controller;

import com.SW.model.User;
import com.SW.businessLogic.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserConrtoller {

    @Autowired
    private UserServices userServices;

    @PostMapping("/register")
    @RequestMapping("/register")
    public String createUser(@RequestBody User user){
        if(user.getType ().equalsIgnoreCase ( "admin" )){
            if(user.getPassword ().contains ( "admin" )){
                userServices.createUser ( user );
            }
            else return "admin password is not valid";
        }
        userServices.createUser ( user );
        return "your account created successfully";
    }

    @GetMapping("/list/{name}")
    public Object getCurrentUsers(@PathVariable  String name){
          return userServices.getCurrentUsers (name);
    }

    @PostMapping("/login")
    @RequestMapping("/login")
    public String login(@RequestParam String email,@RequestParam String password){
       User user =  userServices.getUser (email);
       if(user.getPassword () .equals ( password ))
           return "login successfully";
       return "log in failed";
    }
    
}
