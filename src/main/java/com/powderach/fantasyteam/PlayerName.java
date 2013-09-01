package com.powderach.fantasyteam;

public class PlayerName {
    private final String firstName;
    private final String surname;

    public PlayerName(String firstName, String surname) {
        this.firstName = firstName;
        this.surname = surname;
    }

    public String firstName() {
        return firstName;
    }

    public String surname() {
        return surname;
    }
}
