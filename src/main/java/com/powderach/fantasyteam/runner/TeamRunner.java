package com.powderach.fantasyteam.runner;

import com.mongodb.DBCollection;
import com.powderach.fantasyteam.Player;
import com.powderach.fantasyteam.PlayerSelectionFactory;
import com.powderach.fantasyteam.Team;
import com.powderach.fantasyteam.TeamSelection;

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
        TeamSelection teamSelection = new TeamSelection(playerSelectionFactory);
        Team team = teamSelection.select();
        String rendered = new TeamRenderer(team, new PlayerCostCalculator()).render();

        System.out.println("rendered = " + rendered);
    }

    private class PlayerCostCalculator {
        private int calculateCost(List<Player>... monkeys) {
            ArrayList<Player> allPlayers = new ArrayList<>();
            for (List<Player> players : newArrayList(monkeys)) {
                allPlayers.addAll(players);
            }
            int cost = 0;
            for (Player player : allPlayers) {
                cost += player.getDouble("cost");
            }
            return cost;
        }
    }

    private class TeamRenderer {
        private final Team team;
        private final PlayerCostCalculator calculator;

        public TeamRenderer(Team team, PlayerCostCalculator calculator) {
            this.team = team;
            this.calculator = calculator;
        }

        public String render() {
            StringBuilder stringBuilder = new StringBuilder();
            List<Player> goalkeepers = team.goalkeepers();
            List<Player> defenders = team.defenders();
            List<Player> midfielders = team.midfielders();
            List<Player> forwards = team.forwards();
            int cost = calculator.calculateCost(goalkeepers, defenders, midfielders, forwards);

            stringBuilder.append("TEAM\n");
            stringBuilder.append("Goalkeepers\n").append(render(goalkeepers)).append("\n");
            stringBuilder.append("Defenders\n").append(render(defenders)).append("\n");
            stringBuilder.append("Midfielders\n").append(render(midfielders)).append("\n");
            stringBuilder.append("Forwards\n").append(render(forwards)).append("\n");
            stringBuilder.append("Cost: £").append(cost);

            return stringBuilder.toString();
        }

        private String render(List<Player> players) {
            StringBuilder stringBuilder = new StringBuilder();

            for (Player player : players) {
                stringBuilder
                        .append(player.get("first_name"))
                        .append(" ")
                        .append(player.get("surname"))
                        .append(" ")
                        .append(player.get("team"))
                        .append(" ")
                        .append("£")
                        .append(player.get("cost"))
                        .append(" ")
                        .append(player.get("selected_by"))
                        .append("\n");
            }
            return stringBuilder.toString();
        }
    }
}
