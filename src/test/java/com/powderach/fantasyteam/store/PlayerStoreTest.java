package com.powderach.fantasyteam.store;

import com.powderach.fantasyteam.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PlayerStoreTest extends StoreTestBase {
    private PlayerStore playerStore;
    private Player expected;

    @Override
    protected void additionalSetup() {
        expected = new Player("Diego", "Lugano", "West Brom", Position.defender, 50, 0.3, 6);
        ArrayList<Player> players = newArrayList(
                expected,
                new Player("Kevin", "Phillips", "Crystal Palace", Position.forward, 45, 1.8, 6),
                new Player("George", "Best", "Man Utd", Position.defender, 70, 0.3, 7)
        );

        for (Player player : players) {
            collectionForTest.insert(player);
        }

        playerStore = new PlayerStore(collectionForTest, new JsonReader(), new JsonToPlayerFactory());
    }

    @Test
    public void retrievesPlayerByName() throws Exception {
        assertThat(playerStore.findPlayerBy(new PlayerName("Diego", "Lugano")), is(expected));
    }

    @Test
    public void throwsExceptionIfPlayerIsNotFound() throws Exception {
        PlayerName unknownPlayerName = new PlayerName("some name");
        try {
            playerStore.findPlayerBy(unknownPlayerName);
            Assert.fail(String.format("Expected [%s]", IllegalArgumentException.class.getSimpleName()));
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), containsString(unknownPlayerName.firstName()));
            assertThat(e.getMessage(), containsString(unknownPlayerName.surname()));
        }
    }
}
