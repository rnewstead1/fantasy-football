package com.powderach.fantasyteam;

public enum Position {
    goalkeeper("Goalkeeper"),
    defender("Defender"),
    midfielder("Midfielder"),
    forward("Forward")
    ;
    private final String display;

    Position(String display) {
        this.display = display;
    }

    public String display() {
        return display;
    }

    public static Position from(String positionAsString) {
        for (Position position : values()) {
            if (positionAsString.endsWith(position.display)) {
                return position;
            }
        }
        throw new IllegalArgumentException(String.format("No position for [%]", positionAsString));
    }
}
