package com.powderach.fantasyteam.store;

import com.powderach.fantasyteam.Player;
import com.powderach.fantasyteam.Position;
import com.powderach.fantasyteam.Team;
import com.powderach.fantasyteam.TeamBuilder;
import org.junit.Test;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TeamStoreTest extends StoreTestBase {

    private TeamStore teamStore;
    private Team team;
    private Player player;

    @Override
    protected void additionalSetup() {
        teamStore = new TeamStore(collectionForTest);
        player = new Player("George", "Best", "Man Utd", Position.defender, 70, 0.3, 7);
        team = new TeamBuilder().with(asList(player)).build();
    }

    @Test
    public void storesTeam() throws Exception {
        teamStore.store(team);

        Player player = (Player) collectionForTest.find().next();
        Team actual = new TeamBuilder().with(asList(player)).build();

        assertThat(actual, is(team));
    }

    @Test
    public void retrievesTeam() throws Exception {
        collectionForTest.insert(player);

        Team actual = teamStore.retrieve();

        assertThat(actual, is(team));
    }
}
