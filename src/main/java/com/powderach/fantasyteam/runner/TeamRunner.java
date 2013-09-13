package com.powderach.fantasyteam.runner;

import com.mongodb.DBCollection;
import com.powderach.fantasyteam.*;
import com.powderach.fantasyteam.renderer.TeamRenderer;
import com.powderach.fantasyteam.store.PlayerSelector;
import com.powderach.fantasyteam.store.PlayerStore;
import com.powderach.fantasyteam.store.TeamStore;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static com.powderach.fantasyteam.store.MongoClientConnector.collectionFor;
import static java.util.Arrays.asList;

public class TeamRunner {
    public static void main(String[] args) {
        Team team = manuallyStoreTeam();
//        Team team = createTeam(teamRunner);

        String rendered = new TeamRenderer(team, new PlayerCostCalculator()).renderToString();
        System.out.println(rendered);
    }

    private static Team manuallyStoreTeam() {
        PlayerStore playerStore = new PlayerStore(collectionFor("playerdb", "player"), new JsonReader(), new JsonToPlayerFactory());
//        System.out.println("Starting to collect data...");
//        playerStore.getDataUpToPlayerNumber(532);
//        System.out.println("Got data!");
        List<Player> players = newArrayList();
        players.addAll(asList(
                playerStore.findPlayerBy(new PlayerName("Simon", "Mignolet")),
                playerStore.findPlayerBy(new PlayerName("Kelvin", "Davis")),
                playerStore.findPlayerBy(new PlayerName("Per", "Mertesacker")),
                playerStore.findPlayerBy(new PlayerName("Javier", "Garrido")),
                playerStore.findPlayerBy(new PlayerName("Ashley", "Williams")),
                playerStore.findPlayerBy(new PlayerName("Nathan", "Baker")),
                playerStore.findPlayerBy(new PlayerName("Branislav", "Ivanovic")),
                playerStore.findPlayerBy(new PlayerName("Jack", "Colback")),
                playerStore.findPlayerBy(new PlayerName("Eden", "Hazard")),
                playerStore.findPlayerBy(new PlayerName("Gnegneri Yaya", "Touré")),
                playerStore.findPlayerBy(new PlayerName("Emboaba", "Oscar")),
                playerStore.findPlayerBy(new PlayerName("Christian", "Benteke")),
                playerStore.findPlayerBy(new PlayerName("Robin", "van Persie")),
                playerStore.findPlayerBy(new PlayerName("Kelvin", "Davis")),
                playerStore.findPlayerBy(new PlayerName("Luke", "Moore"))
        ));


        Team team = new TeamBuilder().with(players).build();
        TeamStore teamStore = new TeamStore(collectionFor("playerdb", "team"));
        teamStore.store(team);
        return team;
    }

    private static Team createTeam() {
        DBCollection playerCollection = collectionFor("playerdb", "player");
        PlayerSelector playerSelector = new PlayerSelector(playerCollection);
        TeamSelector teamSelector = new TeamSelector(playerSelector, null);
        return teamSelector.selectAndStore();
    }

}
