package com.powderach.fantasyteam;

import java.util.Collections;
import java.util.List;

public class TransferSuggester {
    public List<Transfer> suggestFor(List<Player> currentPlayers) {
        Collections.sort(currentPlayers, new PlayerValueComparator());

        //get the two players with the lowest points, then lookup players
        // of the same position for the same or less price who have more points

        return null;
    }

}
