package com.powderach.fantasyteam;

import com.mongodb.BasicDBObject;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class PlayerName extends BasicDBObject {
    private final String firstName;
    private final String surname;

    public PlayerName(String firstName, String surname) {
        this.put("first_name", firstName);
        this.put("surname", surname);
        this.firstName = firstName;
        this.surname = surname;
    }

    public PlayerName() {
        this.firstName = "";
        this.surname = "";
    }

    public String firstName() {
        return firstName;
    }

    public String surname() {
        return surname;
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
