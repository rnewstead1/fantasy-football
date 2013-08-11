package com.powderach.fantasyteam;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;

public class PlayerSelectionFactory {
    private final PlayerSelection playerSelection;

    public PlayerSelectionFactory(DBCollection playerCollection) {
        this.playerSelection = new PlayerSelection(playerCollection);
    }

    public Player cheapest(Position position) {
        return playerSelection.select(new BasicDBObject("position", position.display()), new BasicDBObject("cost", 1));
    }

    public Player mostPopular(Position position) {
        return playerSelection.select(new BasicDBObject("position", position.display()), new BasicDBObject("selected_by", -1));
    }

}
