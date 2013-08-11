package com.powderach.fantasyteam;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

public class PlayerSelection {
    private final DBCollection playerCollection;

    public PlayerSelection(DBCollection playerCollection) {
        this.playerCollection = playerCollection;
        playerCollection.setObjectClass(Player.class);
    }

    public Player select(BasicDBObject query, BasicDBObject sortOrder) {
        try (DBCursor cursor = playerCollection.find(query)) {
            DBCursor sortedCursor = cursor.sort(sortOrder).limit(1);
            while (sortedCursor.hasNext()) {
                return (Player) sortedCursor.next();
            }
        }
        throw new IllegalStateException("Fail");
    }

    public Player select(BasicDBObject query, BasicDBObject sort1, BasicDBObject sort2) {
        try (DBCursor cursor = playerCollection.find(query)) {
            DBCursor sortedCursor = cursor.sort(sort1).sort(sort2).limit(1);
            while (sortedCursor.hasNext()) {
                return (Player) sortedCursor.next();
            }
        }
        throw new IllegalStateException("Fail");
    }
}
