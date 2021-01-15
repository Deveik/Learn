package org.xxx.modules.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xxx.modules.service.UserService;

/**
 * @author Deveik
 */
@RestController
public class UserController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserService userService;

    @PostMapping
    public String logSomething() throws InterruptedException {
        return userService.logSomething();
    }
}
