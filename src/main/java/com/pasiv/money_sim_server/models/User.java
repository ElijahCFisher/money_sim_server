package com.pasiv.money_sim_server.models;


import com.pasiv.money_sim_server.models.sources.House;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Date;

public class User {
    double cash, stocks;
    public Date currentDay;
    ArrayList<House> houses = new ArrayList<>();

    public User(JSONArray initial) {
        JSONObject settings = (JSONObject) initial.get(0);
        cash = (double) settings.get("cash");
        stocks = (double) settings.get("stocks");

        String date = ((JSONObject) settings.get("start")).get("val") + "";
        currentDay = new Date(Integer.parseInt(date.substring(date.indexOf("/")+1))-1900, Integer.parseInt(date.substring(date.indexOf("-")+1, date.indexOf("/")+1))-1, Integer.parseInt(date.substring(0, date.indexOf("-"))));

        for (Object _function : initial) {
            JSONObject function = (JSONObject) _function;
            String name = function.get("type") + "";
            if (!name.equals("house")) continue;
            houses.add(new House(function));
        }
    }
}
