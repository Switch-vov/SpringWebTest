package com.netease.test.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// 指明该类是一个控制器
@Controller
// 将该类加一个链接地址
@RequestMapping(value = "/hello")
public class HelloController {

    // 将该方法加一个链接地址
    @RequestMapping(value = "/spring")
    // 处理方法
    public void spring(HttpServletResponse response) throws IOException {
        response.getWriter().write("Hello, Spring Web!!");
    }
}

