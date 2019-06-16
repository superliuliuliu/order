package com.lgy.order.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@Slf4j
public class SessionController {
    @GetMapping(value = "/set")
    public String set(HttpSession httpSession){
        httpSession.setAttribute("user", "734190426l");
        return "test";
    }

    @GetMapping(value = "/get")
    public String get(HttpSession session){
        return session.getAttribute("user").toString();
    }
}
