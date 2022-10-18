package com.sosnilosm.webapp.start.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Sergei Sosnilo
 */
@Controller
@RequestMapping()
public class IndexController{
    @GetMapping()
    public String index() {
        return "/index";
    }
}
