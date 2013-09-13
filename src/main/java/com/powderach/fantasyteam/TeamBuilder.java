package com.powderach.fantasyteam;

import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;

public class TeamBuilder {
    private Map<Position, List<Player>> team = newHashMap();

    public Team build() {
        return new Team(team);
    }

    public TeamBuilder with(Player player) {
        Position position = player.position();
        if (team.containsKey(position)) {
            List<Player> existingPlayers = team.get(position);
            existingPlayers.add(player);
            team.remove(position);

            team.put(position, existingPlayers);
        } else {
            team.put(position, newArrayList(player));
        }
        return this;
    }

    public TeamBuilder with(List<Player> players) {
        for (Player player : players) {
            with(player);
        }
        return this;
    }
}
