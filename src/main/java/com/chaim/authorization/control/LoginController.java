package com.chaim.authorization.control;

import com.chaim.authorization.model.entity.Login;
import com.chaim.authorization.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author chaim67
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    private UserService userService;


    @PostMapping
    public String login() {return "hello world";}

    @GetMapping(value = "/info")
    @ResponseStatus(HttpStatus.OK)
    public List<Login> findAllResources() {
        return userService.findAllLoginInfo();
    }
}
