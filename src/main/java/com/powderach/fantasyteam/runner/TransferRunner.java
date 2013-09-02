package com.powderach.fantasyteam.runner;

import com.powderach.fantasyteam.JsonReader;
import com.powderach.fantasyteam.JsonToPlayerFactory;
import com.powderach.fantasyteam.LatestPointsCalculator;
import com.powderach.fantasyteam.PlayerName;
import com.powderach.fantasyteam.store.PlayerStore;
import com.powderach.fantasyteam.store.TeamStore;

import java.util.Map;

public class TransferRunner {

    public static void main(String[] args) {
        PlayerStore playerStore = new PlayerStore(new JsonReader(), new JsonToPlayerFactory());
        playerStore.getDataUpToPlayerNumber(532);

        LatestPointsCalculator latestPointsCalculator = new LatestPointsCalculator(new PlayerStore(new JsonReader(), new JsonToPlayerFactory()));
        Map<PlayerName, Long> currentPlayerPoints = latestPointsCalculator.latestPointsFor(new TeamStore().retrieve());
    }
}
