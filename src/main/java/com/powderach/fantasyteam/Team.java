package com.powderach.fantasyteam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.powderach.fantasyteam.Position.*;

public class Team {
    private Map<Position, List<Player>> players;

    public Team() {
        players = new HashMap<>();
    }

    public void addGoalkeepers(List<Player> goalkeepers) {
       addToPlayers(goalkeeper, goalkeepers);
    }

    public void addForwards(List<Player> forwards) {
        addToPlayers(forward, forwards);
    }

    public void addMidfielders(List<Player> midfielders) {
        addToPlayers(midfielder, midfielders);
    }

    public void addDefenders(List<Player> defenders) {
        addToPlayers(defender, defenders);
    }

    private void addToPlayers(Position position, List<Player> playersToAdd) {
        if (players.containsKey(position)) {
            playersToAdd.addAll(players.get(position));
            players.remove(position);
            players.put(position, playersToAdd);
        }
        else {
            players.put(position, playersToAdd);
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
}
