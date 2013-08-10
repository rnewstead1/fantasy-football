package com.powderach.fantasyteam;

import com.mongodb.DBCollection;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static com.google.common.collect.Lists.newArrayList;
import static com.powderach.fantasyteam.store.MongoClientConnector.collectionFor;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CheapestPlayerSelectorTest {

    private DBCollection playerCollection;
    private Player cheapestDefender;

    @Before
    public void setUp() throws Exception {
        playerCollection = collectionFor("testdb", "testCollection");
        cheapestDefender = new Player("Diego", "Lugano", "West Brom", Position.defender, 0, 50, 0.3);
        ArrayList<Player> players = newArrayList(
                cheapestDefender,
                new Player("Kevin", "Phillips", "Crystal Palace", Position.forward, 0, 45, 1.8),
                new Player("George", "Best", "Man Utd", Position.defender, 0, 70, 0.3)
        );

        for (Player player : players) {
            playerCollection.insert(player);
        }
    }

    @Test
    public void findsCheapestDefender() throws Exception {
        CheapestPlayerSelector cheapestPlayerSelector = new CheapestPlayerSelector(playerCollection);

        Player cheapest = cheapestPlayerSelector.cheapest(Position.defender);

        assertThat(cheapest, is(cheapestDefender));
    }

}
