package com.powderach.fantasyteam;

import com.google.common.collect.Iterables;
import com.mongodb.BasicDBObject;

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
        return newArrayList(Iterables.concat(goalkeepers(), defenders(), midfielders(), forwards()));
    }

}
