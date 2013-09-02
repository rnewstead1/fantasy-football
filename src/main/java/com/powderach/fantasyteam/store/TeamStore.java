package com.powderach.fantasyteam.store;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.powderach.fantasyteam.Team;

import static com.powderach.fantasyteam.store.MongoClientConnector.collectionFor;

public class TeamStore {
    private final DBCollection teamCollection;

    public TeamStore() {
        teamCollection = collectionFor("playerdb", "team");
    }

    public void store(Team team) {
        teamCollection.drop();
        teamCollection.insert(team);
    }

    public Team retrieve() {
        DBCursor cursor = teamCollection.find();
        return (Team) cursor.next();
    }
}
