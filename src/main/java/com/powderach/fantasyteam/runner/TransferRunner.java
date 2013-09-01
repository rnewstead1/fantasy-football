package com.powderach.fantasyteam.runner;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.powderach.fantasyteam.JsonReader;
import com.powderach.fantasyteam.JsonToPlayerFactory;
import com.powderach.fantasyteam.Player;
import com.powderach.fantasyteam.Team;
import com.powderach.fantasyteam.store.DataCollection;

import static com.powderach.fantasyteam.store.MongoClientConnector.collectionFor;

public class TransferRunner {

    public static void main(String[] args) {
        DataCollection dataCollection = new DataCollection(new JsonReader(), new JsonToPlayerFactory());
        dataCollection.getDataUpToPlayerNumber(532);

    }


    public static class LatestPointsCalculator {
        private DBCollection playerCollection;

        public LatestPointsCalculator() {
            playerCollection = collectionFor("playerdb", "player");
        }

        public void latestPointsFor(Team team) {
            for (Player player : team.allPlayers()) {
                DBCursor cursor = playerCollection.find(
                        new BasicDBObject("first_name", player.firstName()),
                        new BasicDBObject("surname", player.surname())
                );
                Player monkey = (Player) cursor.next();
            }
        }
    }


}
