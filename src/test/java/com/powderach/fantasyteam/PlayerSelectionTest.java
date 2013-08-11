package com.powderach.fantasyteam;

import com.mongodb.BasicDBObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static com.powderach.fantasyteam.Position.defender;
import static com.powderach.fantasyteam.Position.forward;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PlayerSelectionTest extends PlayerSelectionTestBase {
    private Player defender1;
    private PlayerSelection playerSelection;
    private Player defender2;
    private Player defender4;
    private Player defender3;

    @Override
    protected void additionalSetup() {
        defender1 = new Player("Eric", "Cantona", "Man Utd", defender, 0, 40, 0.3);
        defender2 = new Player("Diego", "Lugano", "West Brom", defender, 0, 50, 0.3);
        defender3 = new Player("Richard", "Best", "Man Utd", defender, 0, 60, 0.3);
        defender4 = new Player("George", "Best", "Man Utd", defender, 0, 70, 0.3);
        ArrayList<Player> players = newArrayList(
                defender2,
                new Player("Kevin", "Phillips", "Crystal Palace", forward, 0, 45, 1.8),
                defender4,
                defender1,
                defender3
        );

        for (Player player : players) {
            playerCollection.insert(player);
        }
        playerSelection = new PlayerSelection(playerCollection);
    }

    @Test
    public void sortsByMultipleFields() throws Exception {
        Player player = playerSelection.select(
                new BasicDBObject("position", defender.display()),
                new BasicDBObject("selected_by", -1),
                new BasicDBObject("cost", 1)
        );

        assertThat(player, is(defender1));
    }

    @Test
    public void returnsListOfPlayers() throws Exception {
        List<Player> expected = newArrayList(defender1, defender2, defender3, defender4);

        List<Player> players = playerSelection.select(
                new BasicDBObject("position", defender.display()),
                4,
                new BasicDBObject("selected_by", -1),
                new BasicDBObject("cost", 1)
        );

        assertThat(players, is(expected));
    }

}
