package com.powderach.fantasyteam;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;

import java.util.List;

public class PlayerSelectionFactory {
    private final PlayerSelection playerSelection;

    public PlayerSelectionFactory(DBCollection playerCollection) {
        this.playerSelection = new PlayerSelection(playerCollection);
    }

    public Player cheapest(Position position) {
        return playerSelection.select(new BasicDBObject("position", position.display()), new BasicDBObject("cost", 1));
    }

    public List<Player> mostSelectedDefenders(int numberToSelect) {
        return mostSelected(Position.defender, numberToSelect);
    }

    public List<Player> mostSelectedMidfielders(int numberToSelect) {
        return mostSelected(Position.midfielder, numberToSelect);
    }

    public List<Player> mostSelectedGoalkeepers(int numberToSelect) {
        return mostSelected(Position.goalkeeper, numberToSelect);
    }

    public List<Player> mostSelectedForwards(int numberToSelect) {
        return mostSelected(Position.forward, numberToSelect);
    }

    private List<Player> mostSelected(Position position, int numberToReturn) {
        return playerSelection.select(
                new BasicDBObject("position", position.display()),
                numberToReturn,
                new BasicDBObject("selected_by", -1),
                new BasicDBObject("cost", 1)
        );
    }

}
