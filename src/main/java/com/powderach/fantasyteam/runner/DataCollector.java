package com.powderach.fantasyteam.runner;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.powderach.fantasyteam.JsonReader;
import com.powderach.fantasyteam.JsonToPlayerFactory;
import com.powderach.fantasyteam.Player;
import org.json.simple.JSONObject;

import java.net.UnknownHostException;

import static java.lang.String.format;

public class DataCollector {
    private static final String REQUEST_URL = "http://fantasy.premierleague.com/web/api/elements/%s/";

    public static void main(String[] args) {
        JsonReader jsonReader = new JsonReader();
        JsonToPlayerFactory jsonToPlayerFactory = new JsonToPlayerFactory();

        MongoClient mongo = mongoClient();
        DB db = mongo.getDB("playerdb");
        DBCollection playerTable = db.getCollection("player");

        for (int i = 1; i <= 532; i++) {
            JSONObject jsonObject = jsonReader.readJsonFromUrl(format(REQUEST_URL, i));
            Player player = jsonToPlayerFactory.createFrom(jsonObject);
            write(player, playerTable);
        }
    }

    private static void write(Player player, DBCollection playerTable) {
        BasicDBObject document = new BasicDBObject();
        document.put("surname", player.surname());
        document.put("first_name", player.firstName());
        document.put("team", player.team());
        document.put("position", player.position());
        document.put("points_last_season", player.lastSeasonPoints());
        document.put("price", player.price());
        document.put("selected_by", player.selectedBy());

        playerTable.insert(document);
    }

    private static MongoClient mongoClient() {
        MongoClient mongo;
        try {
            mongo = new MongoClient();
        } catch (UnknownHostException e) {
            throw new RuntimeException("Something went wrong with Mongo Db", e);
        }
        return mongo;
    }
}
