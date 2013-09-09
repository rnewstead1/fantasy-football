package com.powderach.fantasyteam.store;

import com.powderach.fantasyteam.Player;
import com.powderach.fantasyteam.Position;
import com.powderach.fantasyteam.Team;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TeamStoreTest extends StoreTestBase {

    private TeamStore teamStore;

    @Override
    protected void additionalSetup() {
        teamStore = new TeamStore(collectionForTest);
    }

    @Test
    public void storesTeam() throws Exception {
        Map<Position, List<Player>> players = newHashMap();
        players.put(Position.defender, asList(new Player("George", "Best", "Man Utd", Position.defender, 70, 0.3, 7)));
        Team team = new Team(players);

        teamStore.store(team);

        assertThat((Team) collectionForTest.find().next(), is(team));
    }
}
