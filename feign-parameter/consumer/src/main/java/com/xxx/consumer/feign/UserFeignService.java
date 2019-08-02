package com.xxx.consumer.feign;


import com.xxx.consumer.po.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "provider")
public interface UserFeignService {

    @RequestMapping(value = "/user/add",method = RequestMethod.GET)
    public String addUser(User user);

    @RequestMapping(value = "/value/update",method = RequestMethod.POST)
    public String updateUser(@RequestBody User user);

}
