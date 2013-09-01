package com.powderach.fantasyteam;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
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
            this.put(position.display(), playerNamesFrom(players, position));
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

    private Collection<PlayerName> playerNamesFrom(Map<Position, List<Player>> players, Position position) {
        Collection<PlayerName> names = Collections2.transform(players.get(position), new Function<Player, PlayerName>() {
            @Override
            public PlayerName apply(Player player) {
                return player.name();
            }
        });
        return names;
    }

}
