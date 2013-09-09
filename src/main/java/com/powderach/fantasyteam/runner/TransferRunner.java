package com.powderach.fantasyteam.runner;

import com.powderach.fantasyteam.JsonReader;
import com.powderach.fantasyteam.JsonToPlayerFactory;
import com.powderach.fantasyteam.LatestPointsCalculator;
import com.powderach.fantasyteam.Player;
import com.powderach.fantasyteam.store.PlayerStore;
import com.powderach.fantasyteam.store.TeamStore;

import java.util.List;

import static com.powderach.fantasyteam.store.MongoClientConnector.collectionFor;

public class TransferRunner {

    public static void main(String[] args) {
        PlayerStore playerStore = new PlayerStore(collectionFor("playerdb", "player"), new JsonReader(), new JsonToPlayerFactory());
        playerStore.getDataUpToPlayerNumber(532);

        LatestPointsCalculator latestPointsCalculator = new LatestPointsCalculator(new PlayerStore(collectionFor("playerdb", "player"), new JsonReader(), new JsonToPlayerFactory()));
        List<Player> currentPlayerPoints = latestPointsCalculator.latestPointsFor(new TeamStore().retrieve());
    }
}
