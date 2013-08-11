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

    public Player select(BasicDBObject query, BasicDBObject... sorts) {
        try (DBCursor cursor = playerCollection.find(query)) {
            DBCursor sortedCursor = sort(cursor, sorts).limit(1);
            while (sortedCursor.hasNext()) {
                return (Player) sortedCursor.next();
            }
        }
        throw new IllegalStateException("Fail");
    }

    private DBCursor sort(DBCursor cursor, BasicDBObject... sorts) {
        for (BasicDBObject sort : sorts) {
            cursor.sort(sort);
        }

        return cursor;
    }
}
