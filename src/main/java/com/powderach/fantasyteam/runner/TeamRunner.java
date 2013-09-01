package com.powderach.fantasyteam.runner;

import com.mongodb.DBCollection;
import com.powderach.fantasyteam.*;
import com.powderach.fantasyteam.renderer.TeamRenderer;

import static com.powderach.fantasyteam.store.MongoClientConnector.collectionFor;

public class TeamRunner {
    private PlayerSelectionFactory playerSelectionFactory;

    public TeamRunner() {
        DBCollection playerCollection = collectionFor("playerdb", "player");
        this.playerSelectionFactory = new PlayerSelectionFactory(playerCollection);
    }

    public static void main(String[] args) {
        TeamRunner teamRunner = new TeamRunner();
        TeamSelection teamSelection = new TeamSelection(teamRunner.playerSelectionFactory);
        Team team = teamSelection.select();

        String rendered = new TeamRenderer(team, new PlayerCostCalculator()).render();
        System.out.println(rendered);
    }

}
