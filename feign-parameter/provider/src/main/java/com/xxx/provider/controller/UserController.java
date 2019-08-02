package com.xxx.provider.controller;

import com.xxx.provider.po.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/add")
    public String addUser(User user){
        return "hello"+user.getName();
    }

    @PostMapping("/update")
    public String updateUser(@RequestBody User user){
        return "hello"+user.getName();
    }

}
