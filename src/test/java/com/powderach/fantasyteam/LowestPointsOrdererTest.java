package com.powderach.fantasyteam;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class LowestPointsOrdererTest {

    private LowestPointsOrderer lowestPointsOrderer;
    private Map<PlayerName, Long> unsortedMap;
    private PlayerName davidBeckam;
    private PlayerName ryanGiggs;
    private PlayerName ericCantona;
    private PlayerName patrickVierra;
    private PlayerName paulScholes;

    @Before
    public void setUp() throws Exception {
        davidBeckam = new PlayerName("David Beckham");
        ryanGiggs = new PlayerName("Ryan Giggs");
        ericCantona = new PlayerName("Eric Cantona");
        patrickVierra = new PlayerName("Patrick Vierra");
        paulScholes = new PlayerName("Paul Scholes");

        unsortedMap = newHashMap();
        unsortedMap.put(ericCantona, 50L);
        unsortedMap.put(ryanGiggs, 1L);
        unsortedMap.put(davidBeckam, 6L);
        unsortedMap.put(patrickVierra, 17L);
        unsortedMap.put(paulScholes, 9L);

        lowestPointsOrderer = new LowestPointsOrderer();
    }

    @Test
    public void ordersByPlayersWithLowestPoints() throws Exception {
        List<PlayerName> expected = asList(ryanGiggs, davidBeckam, paulScholes, patrickVierra, ericCantona);

        Map<PlayerName, Long> sorted = lowestPointsOrderer.sortByLowestPoints(unsortedMap);

        assertThat(new ArrayList<PlayerName>(sorted.keySet()), is(expected));
    }
}
