package com.powderach.fantasyteam.store;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.powderach.fantasyteam.JsonReader;
import com.powderach.fantasyteam.JsonToPlayerFactory;
import com.powderach.fantasyteam.Player;
import com.powderach.fantasyteam.PlayerName;
import org.json.simple.JSONObject;

import static com.powderach.fantasyteam.store.MongoClientConnector.collectionFor;
import static java.lang.String.format;

public class DataCollection {
    private static final String REQUEST_URL = "http://fantasy.premierleague.com/web/api/elements/%s/";

    private final JsonReader jsonReader;
    private final JsonToPlayerFactory jsonToPlayerFactory;
    private final DBCollection playerCollection;

    public DataCollection(JsonReader jsonReader, JsonToPlayerFactory jsonToPlayerFactory) {
        this.jsonReader = jsonReader;
        this.jsonToPlayerFactory = jsonToPlayerFactory;
        this.playerCollection = collectionFor("playerdb", "player");
        playerCollection.drop();
    }

    public static Player findPlayerBy(PlayerName playerName) {
        DBCursor cursor = playerCollection.find(
                new BasicDBObject("first_name", playerName.firstName()),
                new BasicDBObject("surname", playerName.surname())
        );
        return (Player) cursor.next();
    }

    public void getDataUpToPlayerNumber(int index) {
        for (int i = 1; i <= index; i++) {
            Optional<JSONObject> jsonObject = jsonReader.readJsonFromUrl(format(REQUEST_URL, i));
            Optional<Player> player = jsonToPlayerFactory.createFrom(jsonObject);
            player.transform(new Function<Player, Boolean>() {
                @Override
                public Boolean apply(Player player) {
                    playerCollection.insert(player);
                    return true;
                }
            });
        }
    }
}
