package com.powderach.fantasyteam;

import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.sort;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PlayerValueComparatorTest {

    @Test
    public void ordersByLowestValueFirst() throws Exception {
        PlayerValueComparator comparator = new PlayerValueComparator();

        Player player1 = playerWithPoints(50L);
        Player player2 = playerWithPoints(17L);
        Player player3 = playerWithPoints(23L);
        Player player4 = playerWithPoints(19L);
        Player player5 = playerWithPoints(32L);
        List<Player> players = asList(player1, player2, player3, player4, player5);

        sort(players, comparator);

        assertThat(players, is(asList(player2, player4, player3, player5, player1)));
    }

    private Player playerWithPoints(Long points) {
        return new Player(null, null, null, Position.defender, 0, null, points);
    }
}
