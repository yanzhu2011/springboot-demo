package com.yz.springbootdemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @RequestMapping("/")
    public String hello() {
        //DriveUtil.covert2Html();
        return "Hello!!!";
    }
}
