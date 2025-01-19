package com.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/{path:[^\\.]*}")
    public String forward() {
        // Vue.js의 index.html로 리다이렉트
        return "forward:/index.html";
    }
}