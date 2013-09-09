package com.powderach.fantasyteam.store;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.powderach.fantasyteam.Team;

public class TeamStore {
    private final DBCollection teamCollection;

    public TeamStore(DBCollection teamCollection) {
        this.teamCollection = teamCollection;
        teamCollection.setObjectClass(Team.class);
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
