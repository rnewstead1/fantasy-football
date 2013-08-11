package com.powderach.fantasyteam;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

import java.util.ArrayList;
import java.util.List;

public class PlayerSelection {
    private final DBCollection playerCollection;

    public PlayerSelection(DBCollection playerCollection) {
        this.playerCollection = playerCollection;
        playerCollection.setObjectClass(Player.class);
    }

    public Player select(BasicDBObject query, BasicDBObject... sorts) {
        return select(query, 1, sorts).get(0);
    }

    public List<Player> select(BasicDBObject query, int numberToReturn, BasicDBObject... sorts) {
        List<Player> players = new ArrayList<>();
        try (DBCursor cursor = playerCollection.find(query)) {
            DBCursor sortedCursor = sort(cursor, sorts).limit(numberToReturn);
            while (sortedCursor.hasNext()) {
                Player player = (Player) sortedCursor.next();
                players.add(player);
            }
            return players;
        }
    }

    private DBCursor sort(DBCursor cursor, BasicDBObject... sorts) {
        for (BasicDBObject sort : sorts) {
            cursor.sort(sort);
        }

        return cursor;
    }
}
