package com.powderach.fantasyteam.store;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.powderach.fantasyteam.Player;
import com.powderach.fantasyteam.Position;

import java.util.Comparator;
import java.util.List;

import static com.powderach.fantasyteam.Position.*;
import static java.util.Collections.sort;

public class PlayerSelector {
    private final PlayerSelection playerSelection;

    public PlayerSelector(DBCollection playerCollection) {
        this.playerSelection = new PlayerSelection(playerCollection);
    }

    public Player cheapest(Position position) {
        return playerSelection.select(new BasicDBObject("position", position.display()), new BasicDBObject("cost", 1));
    }

    public List<Player> mostSelectedDefenders(int numberToSelect) {
        return mostSelected(defender, numberToSelect);
    }

    public List<Player> mostSelectedMidfielders(int numberToSelect) {
        return mostSelected(midfielder, numberToSelect);
    }

    public List<Player> mostSelectedGoalkeepers(int numberToSelect) {
        return mostSelected(goalkeeper, numberToSelect);
    }

    public List<Player> mostSelectedForwards(int numberToSelect) {
        return mostSelected(forward, numberToSelect);
    }

    public List<Player> cheapestMidfieldersInMostMostPopular(int numberToReturn, int numberToPickFrom) {
        return cheapestPlayersFrom(mostSelectedMidfielders(numberToPickFrom), numberToReturn);
    }

    public List<Player> cheapestDefendersInMostPopular(int numberToReturn, int numberToPickFrom) {
        return cheapestPlayersFrom(mostSelectedDefenders(numberToPickFrom), numberToReturn);
    }

    public List<Player> cheapestForwardInMostPopular(int numberToReturn, int numberToPickFrom) {
        return cheapestPlayersFrom(mostSelectedForwards(numberToPickFrom), numberToReturn);
    }

    public List<Player> cheapestGoalkeeperInMostPopular(int numberToReturn, int numberToPickFrom) {
        return cheapestPlayersFrom(mostSelectedGoalkeepers(numberToPickFrom), numberToReturn);
    }

    private List<Player> cheapestPlayersFrom(List<Player> top20MostPopular, int numberToReturn) {
        sort(top20MostPopular, new PlayerCostComparator());
        return top20MostPopular.subList(0, numberToReturn);
    }

    private List<Player> mostSelected(Position position, int numberToReturn) {
        return playerSelection.select(
                new BasicDBObject("position", position.display()),
                numberToReturn,
                new BasicDBObject("selected_by", -1)
        );
    }

    public List<Player> playersOfTheSamePositionAs(Player currentPlayer) {
        return null;
    }

    private static class PlayerCostComparator implements Comparator<Player> {
        @Override
        public int compare(Player player1, Player player2) {
            double player1Cost = player1.getDouble("cost");
            double player2Cost = player2.getDouble("cost");

            if (player1Cost > player2Cost) {
                return 1;
            } else if (player2Cost > player1Cost) {
                return -1;
            }
            return 0;
        }
    }
}
