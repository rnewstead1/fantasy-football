package com.powderach.fantasyteam;

import com.google.common.base.Function;
import com.mongodb.DBCollection;
import com.sun.istack.internal.Nullable;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;

import static com.google.common.collect.Collections2.transform;
import static com.google.common.collect.Lists.newArrayList;
import static com.powderach.fantasyteam.store.MongoClientConnector.collectionFor;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@Ignore
public class CheapestPlayerSelectorTest {

    private DBCollection playerCollection;
    private Player cheapestDefender;

    @Before
    public void setUp() throws Exception {
        playerCollection = collectionFor("testdb", "testCollection");
        populateTestData();
        cheapestDefender = new Player("Diego", "Lugano", "West Brom", Position.defender, 0, 50, 0.3);
    }

    @Test
    public void findsCheapestDefender() throws Exception {
        CheapestPlayerSelector cheapestPlayerSelector = new CheapestPlayerSelector(playerCollection);

        Player cheapest = cheapestPlayerSelector.cheapest(Position.defender);

        assertThat(cheapest, is(cheapestDefender));
    }

    private void populateTestData() {
        ArrayList<Player> players = newArrayList(
                cheapestDefender,
                new Player("Kevin", "Phillips", "Crystal Palace", Position.forward, 0, 45, 1.8),
                new Player("George", "Best", "Man Utd", Position.defender, 0, 70, 0.3)
        );

        transform(players, new Function<Player, Boolean>() {
            @Override
            public Boolean apply(@Nullable Player player) {
                playerCollection.insert(player);
                return true;
            }
        });

    }
}
