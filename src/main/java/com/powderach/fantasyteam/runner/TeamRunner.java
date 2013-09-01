package com.powderach.fantasyteam.runner;

import com.mongodb.DBCollection;
import com.powderach.fantasyteam.*;
import com.powderach.fantasyteam.renderer.TeamRenderer;

import static com.powderach.fantasyteam.store.MongoClientConnector.collectionFor;

public class TeamRunner {
    private PlayerSelector playerSelector;

    public TeamRunner() {
        DBCollection playerCollection = collectionFor("playerdb", "player");
        this.playerSelector = new PlayerSelector(playerCollection);
    }

    public static void main(String[] args) {
        TeamRunner teamRunner = new TeamRunner();
        TeamSelector teamSelector = new TeamSelector(teamRunner.playerSelector, null);
        Team team = teamSelector.selectAndStore();

        String rendered = new TeamRenderer(team, new PlayerCostCalculator()).render();
        System.out.println(rendered);
    }

}
