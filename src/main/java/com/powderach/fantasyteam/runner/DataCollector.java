package com.powderach.fantasyteam.runner;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.powderach.fantasyteam.JsonReader;
import com.powderach.fantasyteam.JsonToPlayerFactory;
import com.powderach.fantasyteam.Player;
import com.sun.istack.internal.Nullable;
import org.json.simple.JSONObject;

import static java.lang.String.format;

public class DataCollector {
    private static final String REQUEST_URL = "http://fantasy.premierleague.com/web/api/elements/%s/";

    public static void main(String[] args) {
        JsonReader jsonReader = new JsonReader();
        JsonToPlayerFactory jsonToPlayerFactory = new JsonToPlayerFactory();

        MongoClient mongo = new MongoClientConnector().mongoClient();
        DB db = mongo.getDB("playerdb");
        final DBCollection playerTable = db.getCollection("player");

        for (int i = 1; i <= 532; i++) {
            Optional<JSONObject> jsonObject = jsonReader.readJsonFromUrl(format(REQUEST_URL, i));
            Optional<Player> player = jsonToPlayerFactory.createFrom(jsonObject);
            player.transform(new Function<Player, Boolean>() {
                @Override
                public Boolean apply(@Nullable com.powderach.fantasyteam.Player player) {
                    write(player, playerTable);
                    return true;
                }
            });
        }
    }

    private static void write(Player player, DBCollection playerTable) {
        BasicDBObject document = new BasicDBObject();
        document.put("surname", player.surname());
        document.put("first_name", player.firstName());
        document.put("team", player.team());
        document.put("position", player.position());
        document.put("points_last_season", player.lastSeasonPoints());
        document.put("cost", player.cost());
        document.put("selected_by", player.selectedBy());

        playerTable.insert(document);
    }

}
