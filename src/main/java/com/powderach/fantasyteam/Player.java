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
    private final int lastSeasonPoints;
    private final int price;
    private final Double selectedBy;

    public Player(String firstName, String surname, String team, String position, int lastSeasonPoints, int price, Double selectedBy) {
        this.firstName = firstName;
        this.surname = surname;
        this.team = team;
        this.position = position;
        this.lastSeasonPoints = lastSeasonPoints;
        this.price = price;
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

    public int lastSeasonPoints() {
        return lastSeasonPoints;
    }

    public int price() {
        return price;
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
