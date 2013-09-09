package com.powderach.fantasyteam.store;

import com.powderach.fantasyteam.Player;
import com.powderach.fantasyteam.Position;
import org.junit.Test;

import java.util.ArrayList;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PlayerSelectorTest extends StoreTestBase {
    private Player cheapestDefender;

    @Override
    protected void additionalSetup() {
        cheapestDefender = new Player("Diego", "Lugano", "West Brom", Position.defender, 50, 0.3, 6);
        ArrayList<Player> players = newArrayList(
                cheapestDefender,
                new Player("Kevin", "Phillips", "Crystal Palace", Position.forward, 45, 1.8, 6),
                new Player("George", "Best", "Man Utd", Position.defender, 70, 0.3, 7)
        );

        for (Player player : players) {
            collectionForTest.insert(player);
        }
    }

    @Test
    public void findsCheapestDefender() throws Exception {
        PlayerSelector playerSelector = new PlayerSelector(collectionForTest);

        Player cheapest = playerSelector.cheapest(Position.defender);

        assertThat(cheapest, is(cheapestDefender));
    }

}
