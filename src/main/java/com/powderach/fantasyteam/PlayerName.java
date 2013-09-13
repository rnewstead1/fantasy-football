package com.powderach.fantasyteam;

import com.mongodb.BasicDBObject;

public class PlayerName extends BasicDBObject {

    public PlayerName(String firstName, String surname) {
        this.put("first_name", firstName);
        this.put("surname", surname);
    }

    public String firstName() {
        return String.valueOf(this.get("first_name"));
    }

    public String surname() {
        return String.valueOf(this.get("surname"));
    }

}
