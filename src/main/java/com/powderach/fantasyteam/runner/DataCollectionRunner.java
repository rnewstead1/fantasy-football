package com.powderach.fantasyteam.runner;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.mongodb.DBCollection;
import com.powderach.fantasyteam.JsonReader;
import com.powderach.fantasyteam.JsonToPlayerFactory;
import com.powderach.fantasyteam.Player;
import com.sun.istack.internal.Nullable;
import org.json.simple.JSONObject;

import static com.powderach.fantasyteam.store.MongoClientConnector.collectionFor;
import static java.lang.String.format;

public class DataCollectionRunner {
    private static final String REQUEST_URL = "http://fantasy.premierleague.com/web/api/elements/%s/";
    private final DBCollection playerCollection;
    private final JsonReader jsonReader;
    private final JsonToPlayerFactory jsonToPlayerFactory;

    public DataCollectionRunner() {
        this.playerCollection = collectionFor("playerdb", "player");
        playerCollection.drop();
        jsonReader = new JsonReader();
        jsonToPlayerFactory = new JsonToPlayerFactory();
    }

    public static void main(String[] args) {
        new DataCollectionRunner().getDataUpToPlayerNumber(532);
    }

    private void getDataUpToPlayerNumber(int index) {
        for (int i = 1; i <= index; i++) {
            Optional<JSONObject> jsonObject = jsonReader.readJsonFromUrl(format(REQUEST_URL, i));
            Optional<Player> player = jsonToPlayerFactory.createFrom(jsonObject);
            player.transform(new Function<Player, Boolean>() {
                @Override
                public Boolean apply(@Nullable Player player) {
                    playerCollection.insert(player);
                    return true;
                }
            });
        }
    }

}
