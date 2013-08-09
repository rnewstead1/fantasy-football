package com.powderach.fantasyteam.store;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import java.net.UnknownHostException;

public class MongoClientConnector {

    public static DBCollection collectionFor(String dbName, String collectionName) {
        MongoClient mongo;
        try {
            mongo = new MongoClient();
        } catch (UnknownHostException e) {
            throw new RuntimeException("Something went wrong with Mongo Db", e);
        }
        DB playerDb = mongo.getDB(dbName);

        return playerDb.getCollection(collectionName);

    }
}
