package com.pasiv.money_sim_server;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @RequestMapping(value="/")
    public static String index() {
        return "Hello World";
    }
}
