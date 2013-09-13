package com.powderach.fantasyteam.store;

import com.mongodb.BasicDBObject;
import com.powderach.fantasyteam.Player;
import com.powderach.fantasyteam.Position;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static com.powderach.fantasyteam.Position.defender;
import static com.powderach.fantasyteam.Position.forward;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PlayerSelectionTest extends StoreTestBase {
    private Player defender1;
    private PlayerSelection playerSelection;
    private Player defender2;
    private Player defender4;
    private Player defender3;

    @Override
    protected void additionalSetup() {
        defender1 = new Player("Eric", "Cantona", "Man Utd", defender, 80, 0.3, 5);
        defender2 = new Player("Diego", "Lugano", "West Brom", defender, 50, 0.3, 5);
        defender3 = new Player("Richard", "Best", "Man Utd", defender, 60, 0.3, 5);
        defender4 = new Player("George", "Best", "Man Utd", defender, 70, 0.3, 5);
        ArrayList<Player> players = newArrayList(
                defender2,
                new Player("Kevin", "Phillips", "Crystal Palace", forward, 45, 1.8, 6),
                defender4,
                defender1,
                defender3,
                new Player("George", "Normand", "Arsenal", Position.defender, 10, 0.1, 8)
        );

        for (Player player : players) {
            collectionForTest.insert(player);
        }
        playerSelection = new PlayerSelection(collectionForTest);
    }

    @Test
    public void returnsListOfPlayers() throws Exception {
        List<Player> players = playerSelection.select(
                new BasicDBObject("position", defender.display()),
                4,
                new BasicDBObject("selected_by", -1)
        );

        assertThat(players.size(), is(4));
        assertThat(players, hasItems(defender1, defender2, defender3, defender4));
    }

}
