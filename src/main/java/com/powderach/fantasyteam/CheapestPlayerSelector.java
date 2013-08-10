package com.powderach.fantasyteam;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

public class CheapestPlayerSelector {

    private final DBCollection playerCollection;

    public CheapestPlayerSelector(DBCollection playerCollection) {
        this.playerCollection = playerCollection;
    }

    public Player cheapest(Position position) {
        BasicDBObject query = new BasicDBObject("position", position.display());
        playerCollection.setObjectClass(Player.class);

        try (DBCursor cursor = playerCollection.find(query)) {
            DBCursor sortedCursor = cursor.sort(new BasicDBObject("cost", 1)).limit(1);
            while (sortedCursor.hasNext()) {
                return (Player) sortedCursor.next();
            }
        }
        throw new IllegalStateException("Fail");
    }

}
