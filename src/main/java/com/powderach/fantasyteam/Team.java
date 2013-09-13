package com.powderach.fantasyteam;

import com.google.common.collect.Iterables;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;
import static com.powderach.fantasyteam.Position.*;
import static java.util.Collections.emptyList;

public class Team {
    private final Map<Position, List<Player>> allPlayers;

    public Team(Map<Position, List<Player>> allPlayers) {
        this.allPlayers = allPlayers;
    }

    public List<Player> goalkeepers() {
        return getPlayersOrEmptyList(goalkeeper);
    }

    public List<Player> defenders() {
        return getPlayersOrEmptyList(defender);
    }

    public List<Player> midfielders() {
        return getPlayersOrEmptyList(midfielder);
    }

    public List<Player> forwards() {
        return getPlayersOrEmptyList(forward);
    }

    public List<Player> allPlayers() {
        return newArrayList(Iterables.concat(goalkeepers(), defenders(), midfielders(), forwards()));
    }

    private List<Player> getPlayersOrEmptyList(Position position) {
        List<Player> players = allPlayers.get(position);
        if (players == null) {
            return emptyList();
        }
        return players;
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
