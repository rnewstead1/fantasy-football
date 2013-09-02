package com.powderach.fantasyteam.runner;

import com.mongodb.DBCollection;
import com.powderach.fantasyteam.*;
import com.powderach.fantasyteam.renderer.TeamRenderer;
import com.powderach.fantasyteam.store.DataCollection;
import com.powderach.fantasyteam.store.TeamStore;

import java.util.List;
import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;
import static com.powderach.fantasyteam.Position.*;
import static com.powderach.fantasyteam.store.MongoClientConnector.collectionFor;
import static java.util.Arrays.asList;

public class TeamRunner {
    private PlayerSelector playerSelector;

    public TeamRunner() {
        DBCollection playerCollection = collectionFor("playerdb", "player");
        DataCollection dataCollection = new DataCollection(new JsonReader(), new JsonToPlayerFactory());
        dataCollection.getDataUpToPlayerNumber(532);
        this.playerSelector = new PlayerSelector(playerCollection);
    }

    public static void main(String[] args) {
        Team team;
//        team = createTeam(teamRunner);
        team = manuallyStoreTeam();

        String rendered = new TeamRenderer(team, new PlayerCostCalculator()).render();
        System.out.println(rendered);
    }

    private static Team manuallyStoreTeam() {
        Map<Position, List<Player>> players = newHashMap();
        players.put(goalkeeper, asList(
                DataCollection.findPlayerBy(new PlayerName("Simon Mignolet")),
                DataCollection.findPlayerBy(new PlayerName("Kelvin Davis"))
        ));

        players.put(defender, asList(
                DataCollection.findPlayerBy(new PlayerName("Per Mertesacker")),
                DataCollection.findPlayerBy(new PlayerName("Javier Garrido")),
                DataCollection.findPlayerBy(new PlayerName("Ashley Williams")),
                DataCollection.findPlayerBy(new PlayerName("Nathan Baker")),
                DataCollection.findPlayerBy(new PlayerName("Branislav Ivanovich"))
        ));

        players.put(midfielder, asList(
                DataCollection.findPlayerBy(new PlayerName("Jack Colback")),
                DataCollection.findPlayerBy(new PlayerName("Eden Hazard")),
                DataCollection.findPlayerBy(new PlayerName("Gnegneri", "Yaya Toure")),
                DataCollection.findPlayerBy(new PlayerName("Emboaba Oscar")),
                DataCollection.findPlayerBy(new PlayerName("Christian Benteke"))
        ));

        players.put(forward, asList(
                DataCollection.findPlayerBy(new PlayerName("Robin", "van Persie")),
                DataCollection.findPlayerBy(new PlayerName("Kelvin Davis")),
                DataCollection.findPlayerBy(new PlayerName("Luke Moore"))
        ));


        Team team = new Team(players);
        TeamStore teamStore = new TeamStore();
        teamStore.store(team);
        return team;
    }

    private static Team createTeam() {
        TeamRunner teamRunner = new TeamRunner();
        TeamSelector teamSelector = new TeamSelector(teamRunner.playerSelector, null);
        return teamSelector.selectAndStore();
    }

}
