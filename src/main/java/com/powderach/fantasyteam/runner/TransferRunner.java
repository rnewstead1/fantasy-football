package com.powderach.fantasyteam.runner;

import com.powderach.fantasyteam.JsonReader;
import com.powderach.fantasyteam.JsonToPlayerFactory;
import com.powderach.fantasyteam.LatestPointsCalculator;
import com.powderach.fantasyteam.Player;
import com.powderach.fantasyteam.store.PlayerStore;
import com.powderach.fantasyteam.store.TeamStore;

import java.util.List;

public class TransferRunner {

    public static void main(String[] args) {
        PlayerStore playerStore = new PlayerStore(new JsonReader(), new JsonToPlayerFactory());
        playerStore.getDataUpToPlayerNumber(532);

        LatestPointsCalculator latestPointsCalculator = new LatestPointsCalculator(new PlayerStore(new JsonReader(), new JsonToPlayerFactory()));
        List<Player> currentPlayerPoints = latestPointsCalculator.latestPointsFor(new TeamStore().retrieve());
    }
}
