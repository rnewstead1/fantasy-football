package com.powderach.fantasyteam.runner;

import com.mongodb.DBCollection;
import com.powderach.fantasyteam.Player;
import com.powderach.fantasyteam.PlayerSelectionFactory;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static com.powderach.fantasyteam.store.MongoClientConnector.collectionFor;

public class TeamRunner {
    private PlayerSelectionFactory playerSelectionFactory;

    public TeamRunner() {
        DBCollection playerCollection = collectionFor("playerdb", "player");
        this.playerSelectionFactory = new PlayerSelectionFactory(playerCollection);
    }

    public static void main(String[] args) {
        TeamRunner teamRunner = new TeamRunner();
        teamRunner.selectTeam();
    }

    private void selectTeam() {
        List<Player> goalkeeper = playerSelectionFactory.mostSelectedGoalkeepers(1);
        List<Player> defenders = playerSelectionFactory.mostSelectedDefenders(4);
        List<Player> midfielders = playerSelectionFactory.mostSelectedMidfielders(4);
        List<Player> forwards = playerSelectionFactory.mostSelectedForwards(2);

        System.out.println("goalkeeper = " + goalkeeper);
        System.out.println("defenders = " + defenders);
        System.out.println("midfielders = " + midfielders);
        System.out.println("forwards = " + forwards);

        ArrayList<Player> allPlayers = new ArrayList<>();
        for (List<Player> players : newArrayList(goalkeeper, defenders, midfielders, forwards)) {
            allPlayers.addAll(players);
        }
        int cost = 0;
        for (Player player : allPlayers) {
            cost += player.getDouble("cost");
        }
        System.out.println("cost = " + cost);
    }
}
