package com.pasiv.money_sim_server;

import org.json.simple.JSONObject;

public class House {
    int price, owed;

    House(int price, int owed){
        this.price = price;
        this.owed = owed;
    }

    public House(JSONObject function) {
        price = (int) function.get("price");
        owed = (int) function.get("owed");
    }
}
