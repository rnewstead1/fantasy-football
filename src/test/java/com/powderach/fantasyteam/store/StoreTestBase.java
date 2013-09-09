package com.powderach.fantasyteam.store;

import com.mongodb.DBCollection;
import org.junit.After;
import org.junit.Before;

import static com.powderach.fantasyteam.store.MongoClientConnector.collectionFor;

public abstract class StoreTestBase {
    protected DBCollection collectionForTest;

    protected void additionalSetup() {
    }

    protected void additionalTearDown() {
    }

    @Before
    public final void setUp() throws Exception {
        collectionForTest = collectionFor("testdb", "testCollection");
        additionalSetup();
    }

    @After
    public final void tearDown() throws Exception {
        collectionForTest.drop();
        additionalTearDown();
    }
}
