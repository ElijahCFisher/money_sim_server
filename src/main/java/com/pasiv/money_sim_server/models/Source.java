package com.pasiv.money_sim_server.models;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Source {
    String name;
    String id;
    SourceRow[] rows;
    boolean editable;

    public Source() {
        this.name = "New Source";
        this.id = UUID.randomUUID() + "";
        this.rows = new SourceRow[0];
        this.editable = false;
    }

    public Source(String name, String id, SourceRow[] rows) {
        this.name = name;
        this.id = id;
        this.rows = rows;
        this.editable = true;
    }
}
