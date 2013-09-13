package com.powderach.fantasyteam.store;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.powderach.fantasyteam.Player;
import com.powderach.fantasyteam.Team;
import com.powderach.fantasyteam.TeamBuilder;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class TeamStore {
    private final DBCollection teamCollection;
    private TeamBuilder teamBuilder;

    public TeamStore(DBCollection teamCollection) {
        this.teamCollection = teamCollection;
        this.teamBuilder = new TeamBuilder();
        teamCollection.setObjectClass(Player.class);
    }

    public void store(Team team) {
        teamCollection.drop();
        for (Player player : team.allPlayers()) {
            teamCollection.insert(player);
        }
    }

    public Team retrieve() {
        DBCursor cursor = teamCollection.find();
        List<Player> players = newArrayList();
        while(cursor.hasNext()) {
            players.add((Player) cursor.next());
        }
        return teamBuilder.with(players).build();
    }
}
