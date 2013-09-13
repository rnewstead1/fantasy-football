package com.powderach.fantasyteam.store;

import com.powderach.fantasyteam.Player;
import com.powderach.fantasyteam.Position;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class PlayerSelectorTest extends StoreTestBase {
    private Player defender1;
    private PlayerSelector playerSelector;
    private Player defender2;

    @Override
    protected void additionalSetup() {
        defender1 = new Player("Diego", "Lugano", "West Brom", Position.defender, 50, 0.3, 6);
        defender2 = new Player("George", "Best", "Man Utd", Position.defender, 70, 0.3, 7);
        ArrayList<Player> players = newArrayList(
                defender1,
                new Player("Kevin", "Phillips", "Crystal Palace", Position.forward, 45, 1.8, 6),
                defender2
        );

        for (Player player : players) {
            collectionForTest.insert(player);
        }

        playerSelector = new PlayerSelector(collectionForTest);
    }

    @Test
    public void findsCheapestDefender() throws Exception {
        Player cheapest = playerSelector.cheapest(Position.defender);

        assertThat(cheapest, is(defender1));
    }

    @Test
    public void getsPlayersOfTheSamePosition() throws Exception {
        List<Player> players = playerSelector.playersOfTheSamePositionAs(defender1);

        assertThat(players, hasItems(defender2));
    }

    @Test
    public void doesNotReturnOriginalPlayer() throws Exception {
        List<Player> players = playerSelector.playersOfTheSamePositionAs(defender1);

        assertThat(players, not(hasItem(defender1)));
    }

}
