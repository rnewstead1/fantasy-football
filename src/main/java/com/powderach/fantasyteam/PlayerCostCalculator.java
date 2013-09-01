package com.powderach.fantasyteam;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class PlayerCostCalculator {
    public int calculateCost(List<Player>... monkeys) {
        ArrayList<Player> allPlayers = new ArrayList<Player>();
        for (List<Player> players : newArrayList(monkeys)) {
            allPlayers.addAll(players);
        }
        int cost = 0;
        for (Player player : allPlayers) {
            cost += player.getDouble("cost");
        }
        return cost;
    }
}
