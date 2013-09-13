package com.powderach.fantasyteam.store;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.powderach.fantasyteam.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerSelection {
    private final DBCollection playerCollection;

    public PlayerSelection(DBCollection playerCollection) {
        this.playerCollection = playerCollection;
        playerCollection.setObjectClass(Player.class);
    }

    public Player first(BasicDBObject query, BasicDBObject sort) {
        return select(query, 1, sort).get(0);
    }

    public List<Player> all(BasicDBObject query) {
        return playersFrom(playerCollection.find(query));
    }

    public List<Player> select(BasicDBObject query, int numberToReturn, BasicDBObject sort) {
        return playersFrom(playerCollection.find(query).sort(sort).limit(numberToReturn));
    }

    private List<Player> playersFrom(DBCursor cursor) {
        List<Player> players = new ArrayList<Player>();
        while (cursor.hasNext()) {
            Player player = (Player) cursor.next();
            players.add(player);
        }
        return players;
    }
}
