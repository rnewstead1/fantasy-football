package com.powderach.fantasyteam;

import java.util.Comparator;

public class PlayerValueComparator implements Comparator<Player> {
    @Override
    public int compare(Player player1, Player player2) {
        Long player1Points = player1.totalPoints();
        Long player2Points = player2.totalPoints();

        if (player1Points > player2Points) {
            return -1;
        } else if (player2Points > player1Points) {
            return 1;
        }
        return 0;
    }
}
