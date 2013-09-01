package com.powderach.fantasyteam;

import com.mongodb.BasicDBObject;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;
import static com.powderach.fantasyteam.Position.*;

public class Team extends BasicDBObject {
    private Map<Position, List<Player>> players;

    public Team(Map<Position, List<Player>> players) {
        this.players = players;
        for (Position position : players.keySet()) {
            this.put(position.display(), players.get(position));
        }
    }

    public List<Player> goalkeepers() {
        return players.get(goalkeeper);
    }

    public List<Player> defenders() {
        return players.get(defender);
    }

    public List<Player> midfielders() {
        return players.get(midfielder);
    }

    public List<Player> forwards() {
        return players.get(forward);
    }

    public Collection<Player> allPlayers() {
        Collection<Player> superList = newArrayList();
        for (List<Player> playerList : players.values()) {
            superList.addAll(playerList);
        }

        return superList;
    }
}
