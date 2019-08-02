package com.xxx.consumer.controller;

import com.xxx.consumer.feign.UserFeignService;
import com.xxx.consumer.po.User;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserFeignService userFeignService;

    @PostMapping("/add")
    public String addUser(@RequestBody @ApiParam(name="用户",value="传入json格式",required=true)  User user){
        return userFeignService.addUser(user);
    }

    @PostMapping("/update")
    public String updateUser(@RequestBody User user){
        return userFeignService.updateUser(user);
    }

}
