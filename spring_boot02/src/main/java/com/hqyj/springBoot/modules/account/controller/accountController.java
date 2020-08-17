package com.hqyj.springBoot.modules.account.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class accountController {

    /*
    * 127.0.0.1/account/users
    * */
    @RequestMapping("/users")
    public String usersPage(){
        return "index";
    }
}
