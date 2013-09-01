package com.powderach.fantasyteam;

import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

public class TeamBuilder {
    private Map<Position, List<Player>> team;

    public Team build() {
        return new Team(team);
    }

    public TeamBuilder with(List<Player> players) {
        for (Player player : players) {
            Position position = player.position();
            if (team.containsKey(position)) {
                List<Player> existingPlayers = team.get(position);
                existingPlayers.add(player);
                team.remove(position);

                team.put(position, existingPlayers);
            } else {
                team.put(position, asList(player));
            }
        }
        return this;
    }
}
