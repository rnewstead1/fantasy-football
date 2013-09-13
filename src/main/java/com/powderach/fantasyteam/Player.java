package com.powderach.fantasyteam;

import com.mongodb.BasicDBObject;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Player extends BasicDBObject {
    private static final String FIRST_NAME = "first_name";
    private static final String SURNAME = "surname";
    private static final String TEAM = "team";
    private static final String POSITION = "position";
    private static final String COST = "cost";
    private static final String SELECTED_BY = "selected_by";
    private static final String POINTS = "points";

    public Player(String firstName, String surname, String team, Position position, long cost, Double selectedBy, long points) {
        this.put(FIRST_NAME, firstName);
        this.put(SURNAME, surname);
        this.put(TEAM, team);
        this.put(POSITION, position.display());
        this.put(COST, cost);
        this.put(SELECTED_BY, selectedBy);
        this.put(POINTS, points);
    }

    public Player() {
        //Needed for Mongo.
    }

    public Position position() {
        return Position.from(String.valueOf(get(POSITION)));
    }

    public PlayerName name() {
        return new PlayerName(String.valueOf(get(FIRST_NAME)), String.valueOf(get(SURNAME)));
    }

    public String team() {
        return String.valueOf(get(TEAM));
    }

    public Long totalPoints() {
        return Long.parseLong(String.valueOf(get(POINTS)));
    }

    public Long cost() {
        return Long.parseLong(String.valueOf(get(COST)));
    }

    public Double selectedBy() {
        return Double.parseDouble(String.valueOf(get(SELECTED_BY)));
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
