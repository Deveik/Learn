package org.xxx.modules.base.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.xxx.modules.base.entity.User;
import org.xxx.modules.base.service.UserService;

import java.util.Random;

/**
 * @author Deveik
 */
@RestController
@RequestMapping("/user")
public class UserController {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    UserService userService;

    @PutMapping( "/decreaseAge/{userId}" )
    public void decreaseAge(@PathVariable("userId") String userId) throws InterruptedException {
        userService.decreaseAge(userId);
    }

    @GetMapping( "/get/{userId}" )
    public User get(@PathVariable("userId") String userId) {
        return userService.getById(userId);
    }
}
