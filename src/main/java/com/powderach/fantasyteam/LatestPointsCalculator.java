package com.powderach.fantasyteam;

import com.powderach.fantasyteam.store.PlayerStore;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class LatestPointsCalculator {
    private final PlayerStore playerStore;

    public LatestPointsCalculator(PlayerStore playerStore) {
        this.playerStore = playerStore;
    }

    public List<Player> latestPointsFor(Team team) {
        List<Player> teamPlayerPoints = newArrayList();
        for (Player player : team.allPlayers()) {
            PlayerName playerName = player.name();
            teamPlayerPoints.add(playerStore.findPlayerBy(playerName));
        }
        return teamPlayerPoints;
        }

}
