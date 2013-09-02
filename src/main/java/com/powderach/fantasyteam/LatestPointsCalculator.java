package com.powderach.fantasyteam;

import com.powderach.fantasyteam.store.DataCollection;

import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

public class LatestPointsCalculator {
    //Player object has points on it but we don't want to look at it...
    public Map<PlayerName, Long> latestPointsFor(Team team) {
        Map<PlayerName, Long> teamPlayerPoints = newHashMap();
        for (Player player : team.allPlayers()) {
            PlayerName playerName = player.name();
            Player retrieved  = DataCollection.findPlayerBy(playerName);
            teamPlayerPoints.put(retrieved.name(), retrieved.totalPoints());
        }
        return teamPlayerPoints;
        }

}
