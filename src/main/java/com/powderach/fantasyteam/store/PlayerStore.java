package com.powderach.fantasyteam.store;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.powderach.fantasyteam.Player;
import com.powderach.fantasyteam.PlayerName;

import static com.powderach.fantasyteam.store.MongoClientConnector.collectionFor;

public class PlayerStore {
    private final DBCollection playerCollection;

    public PlayerStore() {
        playerCollection = collectionFor("playerdb", "player");
    }

    public Player findPlayerBy(PlayerName playerName) {
        DBCursor cursor = playerCollection.find(
                new BasicDBObject("first_name", playerName.firstName()),
                new BasicDBObject("surname", playerName.surname())
        );
        return (Player) cursor.next();
    }
}
