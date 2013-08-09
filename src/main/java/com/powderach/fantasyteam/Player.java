package com.powderach.fantasyteam;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Player {
    private final String firstName;
    private final String surname;
    private final String team;
    private final String position;
    private final long lastSeasonPoints;
    private final long cost;
    private final Double selectedBy;

    public Player(String firstName, String surname, String team, String position, long lastSeasonPoints, long cost, Double selectedBy) {
        this.firstName = firstName;
        this.surname = surname;
        this.team = team;
        this.position = position;
        this.lastSeasonPoints = lastSeasonPoints;
        this.cost = cost;
        this.selectedBy = selectedBy;
    }

    public String surname() {
        return surname;
    }

    public String firstName() {
        return firstName;
    }

    public String team() {
        return team;
    }

    public String position() {
        return position;
    }

    public long lastSeasonPoints() {
        return lastSeasonPoints;
    }

    public long cost() {
        return cost;
    }

    public double selectedBy() {
        return selectedBy;
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
