package com.powderach.fantasyteam;

import com.mongodb.BasicDBObject;
import org.junit.Test;

import java.util.ArrayList;

import static com.google.common.collect.Lists.newArrayList;
import static com.powderach.fantasyteam.Position.defender;
import static com.powderach.fantasyteam.Position.forward;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PlayerSelectionTest extends PlayerSelectionTestBase {
    private Player expected;

    @Override
    protected void additionalSetup() {
        expected = new Player("Eric", "Cantona", "Man Utd", defender, 0, 40, 0.3);
        ArrayList<Player> players = newArrayList(
                new Player("Diego", "Lugano", "West Brom", defender, 0, 50, 0.3),
                new Player("Kevin", "Phillips", "Crystal Palace", forward, 0, 45, 1.8),
                new Player("George", "Best", "Man Utd", defender, 0, 70, 0.3),
                new Player("Richard", "Best", "Man Utd", defender, 0, 60, 0.3),
                expected
        );

        for (Player player : players) {
            playerCollection.insert(player);
        }
    }

    @Test
    public void sortsByMultipleFields() throws Exception {
        PlayerSelection playerSelection = new PlayerSelection(playerCollection);

        Player player = playerSelection.select(
                new BasicDBObject("position", defender.display()),
                new BasicDBObject("selected_by", -1),
                new BasicDBObject("cost", 1)
        );

        assertThat(player, is(expected));
    }

}
