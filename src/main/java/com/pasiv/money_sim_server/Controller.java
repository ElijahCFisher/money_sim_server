package com.pasiv.money_sim_server;

import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.json.simple.JSONArray;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.FileReader;

@RestController
public class Controller {

    @RequestMapping(value="/")
    public static String index() {
        return "Hello World";
    }

    @RequestMapping(value="/calculate/{userId}")
    public static String calculate(@PathVariable(value="userID") String userId) throws FileNotFoundException, ParseException {
        JSONParser parser = new JSONParser(new FileReader("./src/main/resources/" + userId + ".json"));
        JSONArray user = (JSONArray) parser.parse();

        Calculator calc = new Calculator(user);

        return calc.calculate();
    }
}
