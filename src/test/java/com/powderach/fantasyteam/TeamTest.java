package com.powderach.fantasyteam;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static com.google.common.collect.Lists.newArrayList;
import static com.powderach.fantasyteam.Position.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsEmptyCollection.empty;

public class TeamTest {

    private List<Player> goalkeepers;
    private List<Player> defenders;
    private List<Player> midfielders;
    private List<Player> forwards;
    private Team team;

    @Before
    public void setUp() throws Exception {
        goalkeepers = players(goalkeeper, "A", "AA");
        defenders = players(defender, "B", "BB");
        midfielders = players(midfielder, "C", "CC");
        forwards = players(forward, "D", "DD");

        Map<Position, List<Player>> players = new HashMap<Position, List<Player>>();
        players.put(goalkeeper, goalkeepers);
        players.put(defender, defenders);
        players.put(midfielder, midfielders);
        players.put(forward, forwards);
        team = new Team(players);
    }

    @Test
    public void retrievesGoalkeepers() throws Exception {
        assertThat(team.goalkeepers(), is(goalkeepers));
    }

    @Test
    public void retrievesDefenders() throws Exception {
        assertThat(team.defenders(), is(defenders));
    }

    @Test
    public void retrievesMidfielders() throws Exception {
        assertThat(team.midfielders(), is(midfielders));
    }

    @Test
    public void retrievesForwards() throws Exception {
        assertThat(team.forwards(), is(forwards));
    }

    @Test
    public void retrievesAllPlayers() throws Exception {
        Collection<Player> allPlayers = newArrayList();
        allPlayers.addAll(goalkeepers);
        allPlayers.addAll(defenders);
        allPlayers.addAll(midfielders);
        allPlayers.addAll(forwards);

        assertThat(team.allPlayers(), is(allPlayers));
    }

    @Test
    public void returnsEmptyListIfThereAreNoPlayers() throws Exception {
        Team emptyTeam = new Team(Collections.<Position, List<Player>>emptyMap());

        assertThat(emptyTeam.goalkeepers(), empty());
        assertThat(emptyTeam.defenders(), empty());
        assertThat(emptyTeam.midfielders(), empty());
        assertThat(emptyTeam.forwards(), empty());
        assertThat(emptyTeam.allPlayers(), empty());
    }

    private List<Player> players(Position position, String firstName, String otherPlayerFirstName) {
        return newArrayList(new Player(firstName, "", "", position, 80, 0.5, 9), new Player(otherPlayerFirstName, "", "", position, 80, 0.5, 9));
    }
}
