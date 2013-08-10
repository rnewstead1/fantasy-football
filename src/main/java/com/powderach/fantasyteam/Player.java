package com.powderach.fantasyteam;

import com.mongodb.BasicDBObject;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;
import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

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

    @Override
    public int hashCode() {
        return reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object o) {
        return reflectionEquals(this, o);
    }

    @Override
    public String toString() {
        return reflectionToString(this, SHORT_PREFIX_STYLE);
    }
}
