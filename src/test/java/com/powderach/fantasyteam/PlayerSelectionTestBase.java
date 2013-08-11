package com.powderach.fantasyteam;

import com.mongodb.DBCollection;
import org.junit.After;
import org.junit.Before;

import static com.powderach.fantasyteam.store.MongoClientConnector.collectionFor;

public abstract class PlayerSelectionTestBase {
    protected DBCollection playerCollection;

    protected void additionalSetup() {
    }

    protected void additionalTearDown() {
    }

    @Before
    public final void setUp() throws Exception {
        playerCollection = collectionFor("testdb", "testCollection");
        additionalSetup();
    }

    @After
    public final void tearDown() throws Exception {
        playerCollection.drop();
        additionalTearDown();
    }
}
