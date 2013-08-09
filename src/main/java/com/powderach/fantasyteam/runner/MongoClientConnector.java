package com.powderach.fantasyteam.runner;

import com.mongodb.MongoClient;

import java.net.UnknownHostException;

public class MongoClientConnector {

    public MongoClient mongoClient() {
        MongoClient mongo;
        try {
            mongo = new MongoClient();
        } catch (UnknownHostException e) {
            throw new RuntimeException("Something went wrong with Mongo Db", e);
        }
        return mongo;
    }
}
