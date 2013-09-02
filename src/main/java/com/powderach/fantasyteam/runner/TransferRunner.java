package com.powderach.fantasyteam.runner;

import com.google.common.base.Functions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Ordering;
import com.powderach.fantasyteam.JsonReader;
import com.powderach.fantasyteam.JsonToPlayerFactory;
import com.powderach.fantasyteam.LatestPointsCalculator;
import com.powderach.fantasyteam.PlayerName;
import com.powderach.fantasyteam.store.DataCollection;
import com.powderach.fantasyteam.store.TeamStore;

import java.util.List;
import java.util.Map;

public class TransferRunner {

    public static void main(String[] args) {
        DataCollection dataCollection = new DataCollection(new JsonReader(), new JsonToPlayerFactory());
        dataCollection.getDataUpToPlayerNumber(532);

        LatestPointsCalculator latestPointsCalculator = new LatestPointsCalculator();
        Map<PlayerName,Long> currentPlayerPoints = latestPointsCalculator.latestPointsFor(new TeamStore().retrieve());

    }


    public static class TransferSuggester {
        public List<Transfer> suggestFor(Map<PlayerName, Long> currentPlayers) {
            Ordering<PlayerName> ordering = Ordering.natural().onResultOf(Functions.forMap(currentPlayers));
            ImmutableList<PlayerName> playerNames = ordering.immutableSortedCopy(currentPlayers.keySet());
            
            return null;
        }
    }

    private static class Transfer {
    }
}
