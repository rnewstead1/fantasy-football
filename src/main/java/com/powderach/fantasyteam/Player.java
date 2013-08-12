package com.powderach.fantasyteam;

import com.mongodb.BasicDBObject;

public class Player extends BasicDBObject {
    private static final String FIRST_NAME = "first_name";
    private static final String SURNAME = "surname";
    private static final String TEAM = "team";
    private static final String POSITION = "position";
    private static final String POINTS_LAST_SEASON = "points_last_season";
    private static final String COST = "cost";
    private static final String SELECTED_BY = "selected_by";

    public Player(String firstName, String surname, String team, Position position, long lastSeasonPoints, long cost, Double selectedBy) {
        this.put(FIRST_NAME, firstName);
        this.put(SURNAME, surname);
        this.put(TEAM, team);
        this.put(POSITION, position.display());
        this.put(POINTS_LAST_SEASON, lastSeasonPoints);
        this.put(COST, cost);
        this.put(SELECTED_BY, selectedBy);
    }

    public Player() {
        //Needed for Mongo.
    }


}
