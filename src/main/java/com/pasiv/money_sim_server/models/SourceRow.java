package com.pasiv.money_sim_server.models;

import lombok.*;

import java.util.HashMap;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SourceRow {
    HashMap<String, String> attributes;
    Source source;

    public SourceRow(HashMap<String, String> attributes) {
        this.attributes = attributes;
    }
    public SourceRow(Source source) {
        this.source = source;
    }
}
