package org.xxx.modules.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.xxx.modules.base.entity.User;
import org.xxx.modules.base.service.UserService;

/**
 * @author Deveik
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PutMapping( "/decreaseAge/{userId}" )
    public void decreaseAge(@PathVariable("userId") String userId) {
        User user = userService.getById(userId);

        if (user.getAge() >= 0) {
            user.setAge(user.getAge() - 1);
            userService.updateById(user);
        }
    }

    @GetMapping( "/get/{userId}" )
    public User get(@PathVariable("userId") String userId) {
        return userService.getById(userId);
    }
}
