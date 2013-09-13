package com.powderach.fantasyteam;

import com.mongodb.BasicDBObject;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;
import static com.powderach.fantasyteam.Position.*;

public class Team extends BasicDBObject {

    public Team(Map<Position, List<Player>> players) {
        for (Position position : players.keySet()) {
            this.put(position.display(), players.get(position));
        }
    }

    public Team() {
    }

    public List<Player> goalkeepers() {
        return (List<Player>) this.get(goalkeeper.display());
    }

    public List<Player> defenders() {
        return (List<Player>) this.get(defender.display());
    }

    public List<Player> midfielders() {
        return (List<Player>) this.get(midfielder.display());
    }

    public List<Player> forwards() {
        return (List<Player>) this.get(forward.display());
    }

    public List<Player> allPlayers() {
        List<Player> superList = newArrayList(goalkeepers());
        superList.addAll(defenders());
        superList.addAll(midfielders());
        superList.addAll(forwards());

        return superList;
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
