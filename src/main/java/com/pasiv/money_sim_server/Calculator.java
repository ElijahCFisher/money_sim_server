package com.pasiv.money_sim_server;


import com.pasiv.money_sim_server.models.sources.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.Date;

public class Calculator {
    JSONArray initialUser;
    User currentUser;

    Calculator(JSONArray user) {
        this.initialUser = user;
        currentUser = new User(user);
    }

    String calculate() {
        java.lang.reflect.Method method;
        for (int i = 0; i < initialUser.size(); i++) {
            try {
                JSONObject function = (JSONObject) initialUser.get(i);
                String name = function.get("type") + "";
                method = this.getClass().getMethod(name);
                method.invoke(this, function, i);
            } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return "";
    }

    void income() {
        Calendar calendar = Calendar.getInstance();
//        if(currentUser.currentDay.getDay() == 5 && currentUser.currentDay.)
    }
    void expenses() {

    }
    void house() {}
    void retirementAccount() {}
    void repeat() {}
    void renting() {}

    int getDay(Date date) {
        int month = date.getMonth();
        int year = date.getYear();
        int ret = (month+2)/2*31; // counting 31 months
        ret -= (month < 7 && month % 2 == 0) ? 31 : 0; // correcting overcounting of 31 months
        ret += (month/3 + (month==11 ? 1 : 0) - (month==3 ? 1 : 0))*30; // counting 30 months (was weird, but this magic worked)
        ret += month > 1 ? 28 : 0; // counting Feb
        ret += (month > 1 && (((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0))) ?
                1 : 0); // counting Feb 29 (close enough)
        return ret + date.getDate();
    }
}
