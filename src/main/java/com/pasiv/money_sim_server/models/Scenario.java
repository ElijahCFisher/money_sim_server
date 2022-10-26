package com.pasiv.money_sim_server.models;

import com.pasiv.money_sim_server.models.Source;
import lombok.*;

import java.util.HashMap;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Scenario {
    int index;
    Source scenarioSettings;
    HashMap<String, Source> scenarioSources;

    @Override
    public boolean equals(Object obj) {
        return obj.getClass().equals(this.getClass()) && obj.toString().equals(this.toString());
    }
}
